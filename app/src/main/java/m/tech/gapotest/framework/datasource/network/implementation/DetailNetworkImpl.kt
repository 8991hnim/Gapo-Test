package m.tech.gapotest.framework.datasource.network.implementation

import m.tech.gapotest.business.data.network.abstraction.DetailNetworkDataSource
import m.tech.gapotest.business.domain.DetailDocument
import m.tech.gapotest.framework.datasource.network.api.DetailApi
import m.tech.gapotest.framework.datasource.network.mappers.DetailDocumentDtoMapper

class DetailNetworkImpl
constructor(
    private val detailApi: DetailApi,
    private val mapper: DetailDocumentDtoMapper
) : DetailNetworkDataSource {

    override suspend fun getDetail(): DetailDocument {
        return mapper.toDomain(detailApi.getNewsFeed())
    }

}