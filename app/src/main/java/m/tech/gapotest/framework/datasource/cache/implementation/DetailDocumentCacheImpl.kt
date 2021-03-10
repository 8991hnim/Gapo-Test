package m.tech.gapotest.framework.datasource.cache.implementation

import m.tech.gapotest.business.data.cache.abstraction.DetailCacheDataSource
import m.tech.gapotest.business.data.cache.abstraction.NewsFeedCacheDataSource
import m.tech.gapotest.business.domain.DetailDocument
import m.tech.gapotest.business.domain.NewsFeed
import m.tech.gapotest.framework.datasource.cache.database.dao.DetailDocumentDao
import m.tech.gapotest.framework.datasource.cache.database.dao.NewsFeedDao
import m.tech.gapotest.framework.datasource.cache.mappers.DetailDocumentEntityMapper
import m.tech.gapotest.framework.datasource.cache.mappers.NewsFeedEntityMapper

class DetailDocumentCacheImpl(
    private val detailDocumentDao: DetailDocumentDao,
    private val mapper: DetailDocumentEntityMapper
) : DetailCacheDataSource {

    override suspend fun getDetail(detailId: String): DetailDocument? {
       return detailDocumentDao.getDetailById(detailId)?.let {
            mapper.toDomain(it)
        }
    }

    override suspend fun addDetail(detail: DetailDocument) {
        detailDocumentDao.addDetail(mapper.fromDomain(detail))
    }

    override suspend fun updateDetail(detail: DetailDocument) {
        detailDocumentDao.updateDetail(mapper.fromDomain(detail))
    }
}