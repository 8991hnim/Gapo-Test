package m.tech.gapotest.framework.datasource.cache.implementation

import m.tech.gapotest.business.data.cache.abstraction.NewsFeedCacheDataSource
import m.tech.gapotest.business.domain.NewsFeed
import m.tech.gapotest.framework.datasource.cache.database.dao.NewsFeedDao
import m.tech.gapotest.framework.datasource.cache.mappers.NewsFeedEntityMapper

class NewsFeedCacheImpl(
    private val newsFeedDao: NewsFeedDao,
    private val mapper: NewsFeedEntityMapper
) : NewsFeedCacheDataSource {
    override suspend fun getNewsFeed(): List<NewsFeed> {
        return mapper.toDomainList(newsFeedDao.getNewsFeed())
    }

    override suspend fun getNewsFeed(page: Int, limit: Int): List<NewsFeed> {
        return mapper.toDomainList(newsFeedDao.getNewsFeed(page, limit))
    }

    override suspend fun addNewsFeed(newsFeed: NewsFeed) {
        newsFeedDao.addNewsFeed(mapper.fromDomain(newsFeed))
    }

    override suspend fun getNewsFeedById(id: String): NewsFeed? {
        return newsFeedDao.getNewsFeedById(id)?.let {
            mapper.toDomain(it)
        }
    }

    override suspend fun updateNewsFeed(newsFeed: NewsFeed) {
        newsFeedDao.updateNewsFeed(mapper.fromDomain(newsFeed))
    }

    override suspend fun deleteNewsFeed(newsFeed: NewsFeed) {
        newsFeedDao.deleteNewsFeed(mapper.fromDomain(newsFeed))
    }
}