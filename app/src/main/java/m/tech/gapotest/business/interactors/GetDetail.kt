package m.tech.gapotest.business.interactors

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import m.tech.gapotest.business.data.DataState
import m.tech.gapotest.business.data.cache.abstraction.DetailCacheDataSource
import m.tech.gapotest.business.data.network.NetworkResult
import m.tech.gapotest.business.data.network.abstraction.DetailNetworkDataSource
import m.tech.gapotest.business.data.safeApiCall
import m.tech.gapotest.business.domain.DetailDocument
import javax.inject.Inject

class GetDetail
@Inject
constructor(
    private val detailCacheDataSource: DetailCacheDataSource,
    private val detailNetworkDataSource: DetailNetworkDataSource
) {

    suspend fun getDetail(
        isFetch: Boolean,
        detailId: String
    ): Flow<DataState<DetailDocument>> = flow {
        emit(DataState.Loading()) // emit loading state
        delay(1500)
        val cache = detailCacheDataSource.getDetail(detailId)

        if (!isFetch) {
            emit(DataState.Success(cache))
        } else {
            val result = safeApiCall(IO) { detailNetworkDataSource.getDetail() }

            val network = when (result) {
                is NetworkResult.Success -> {
                    result.value
                }
                is NetworkResult.GenericError -> {
                    emit(DataState.Error(result.code, cache))
                    return@flow
                }
            }

            if (network != null) {
                syncDetail(cache, network)
            }


            emit(DataState.Success(detailCacheDataSource.getDetail(detailId)))
        }
    }

    private suspend fun syncDetail(
        cache: DetailDocument?,
        network: DetailDocument
    ) = withContext(IO) {
        cache?.let {
            detailCacheDataSource.updateDetail(network)
        } ?: detailCacheDataSource.addDetail(network)
    }

}
