package m.tech.gapotest.business.data.cache.abstraction

import m.tech.gapotest.business.domain.Dummy
import m.tech.gapotest.business.domain.NewsFeed

interface NewsFeedCacheDataSource {

    suspend fun getNewsFeed(): List<NewsFeed>

    suspend fun getNewsFeed(page: Int, limit: Int): List<NewsFeed>

    suspend fun addNewsFeed(newsFeed: NewsFeed)

    suspend fun getNewsFeedById(id: String): NewsFeed?

    suspend fun updateNewsFeed(newsFeed: NewsFeed)

    suspend fun deleteNewsFeed(newsFeed: NewsFeed)

}