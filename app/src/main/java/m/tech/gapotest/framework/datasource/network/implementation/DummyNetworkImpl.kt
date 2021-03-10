package m.tech.gapotest.framework.datasource.network.implementation

import m.tech.gapotest.business.data.network.abstraction.DummyNetworkDataSource
import m.tech.gapotest.business.domain.Dummy
import m.tech.gapotest.framework.datasource.network.api.DummyApi
import m.tech.gapotest.framework.datasource.network.mappers.DummyDtoMapper

class DummyNetworkImpl
constructor(
    private val dummyApi: DummyApi,
    private val dummyDtoMapper: DummyDtoMapper
) : DummyNetworkDataSource {

    override suspend fun getDummies(): List<Dummy> =
        dummyDtoMapper.toDomainList(dummyApi.getDummies())

}