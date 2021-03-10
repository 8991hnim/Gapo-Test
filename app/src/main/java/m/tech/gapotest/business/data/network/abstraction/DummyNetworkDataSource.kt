package m.tech.gapotest.business.data.network.abstraction

import m.tech.gapotest.business.domain.Dummy

interface DummyNetworkDataSource {

    suspend fun getDummies(): List<Dummy>

}