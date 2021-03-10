package m.tech.gapotest.framework.datasource.network.implementation

import m.tech.gapotest.business.data.network.abstraction.NewsFeedNetworkDataSource
import m.tech.gapotest.business.domain.NewsFeed
import m.tech.gapotest.framework.datasource.network.api.NewsFeedApi
import m.tech.gapotest.framework.datasource.network.mappers.NewsFeedDtoMapper

class NewsFeedNetworkImpl
constructor(
    private val newsFeedApi: NewsFeedApi,
    private val mapper: NewsFeedDtoMapper
) : NewsFeedNetworkDataSource {

    override suspend fun getNewsFeed(): List<NewsFeed> {
       return mapper.toDomainList(newsFeedApi.getNewsFeed().data)
    }

}