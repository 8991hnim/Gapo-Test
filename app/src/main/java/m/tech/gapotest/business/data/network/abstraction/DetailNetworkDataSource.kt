package m.tech.gapotest.business.data.network.abstraction

import m.tech.gapotest.business.domain.DetailDocument

interface DetailNetworkDataSource {

    suspend fun getDetail(): DetailDocument

}