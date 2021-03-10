package m.tech.gapotest.business.interactors

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import m.tech.gapotest.business.data.DataState
import m.tech.gapotest.business.data.cache.abstraction.NewsFeedCacheDataSource
import m.tech.gapotest.business.data.network.NetworkResult
import m.tech.gapotest.business.data.network.abstraction.NewsFeedNetworkDataSource
import m.tech.gapotest.business.data.safeApiCall
import m.tech.gapotest.business.domain.NewsFeed
import m.tech.gapotest.business.interactors.UseCaseEx.removeFromList
import javax.inject.Inject

class GetNewsFeed
@Inject
constructor(
    private val newsFeedCacheDataSource: NewsFeedCacheDataSource,
    private val newsFeedNetworkDataSource: NewsFeedNetworkDataSource
) {

    suspend fun getNewsFeed(
        isFetch: Boolean,
        page: Int,
        limit: Int
    ): Flow<DataState<List<NewsFeed>>> = flow {
        emit(DataState.Loading()) // emit loading state

        val caches = newsFeedCacheDataSource.getNewsFeed(page, limit)

        if (!isFetch) {
            emit(DataState.Success(caches))
        } else {
            val result = safeApiCall(IO) {
                newsFeedNetworkDataSource.getNewsFeed()
            }

            val networkNewsFeed = when (result) {
                is NetworkResult.Success -> {
                    result.value ?: ArrayList()
                }
                is NetworkResult.GenericError -> {
                    emit(DataState.Error(result.code, caches))
                    return@flow
                }
            }

            syncNewsFeed(newsFeedCacheDataSource.getNewsFeed().toMutableList(), networkNewsFeed)

            emit(DataState.Success(newsFeedCacheDataSource.getNewsFeed(page, limit)))
        }

    }

    /*
     *** Sync data online and offline (network to db)
     * 1: Loop through network list
     * 2: Find that network object in db:
         If don't have -> insert, else -> update, then remove from cache list
     * 3: Remove any cache objects left in the db (that objects do not exists in network list)
     ==============
     * note: in some case, you need to sync from cache to network too (in both way)
     * in step 2: check for updated date to sync properly
     * in step 3: instead delete it, upload it to server
  */
    private suspend fun syncNewsFeed(
        caches: MutableList<NewsFeed>,
        networks: List<NewsFeed>
    ) = withContext(IO) {
        val promise = async {
            for (newsFeed in networks) {
                launch {
                    newsFeedCacheDataSource.getNewsFeedById(newsFeed.baseDocument.documentId)
                        ?.let { cached ->
                            removeFromList(caches, cached)
                            newsFeedCacheDataSource.updateNewsFeed(newsFeed)
                        } ?: newsFeedCacheDataSource.addNewsFeed(newsFeed)
                }
            }
        }
        promise.await()

        for (cached in caches) {
            launch {
                newsFeedCacheDataSource.deleteNewsFeed(cached)
            }
        }

    }

}
