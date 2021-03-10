package m.tech.gapotest.business.data.network.abstraction

import m.tech.gapotest.business.domain.NewsFeed

interface NewsFeedNetworkDataSource {

    suspend fun getNewsFeed(): List<NewsFeed>

}