package m.tech.gapotest.business.data.cache.abstraction

import m.tech.gapotest.business.domain.DetailDocument

interface DetailCacheDataSource {
    suspend fun addDetail(detail: DetailDocument)

    suspend fun getDetail(detailId: String): DetailDocument?

    suspend fun updateDetail(detail: DetailDocument)
}
