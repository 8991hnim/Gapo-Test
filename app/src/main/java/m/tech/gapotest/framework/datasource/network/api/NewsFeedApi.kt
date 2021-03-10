package m.tech.gapotest.framework.datasource.network.api

import m.tech.gapotest.framework.datasource.network.api.NetworkConstants.NEWS_FEED
import m.tech.gapotest.framework.datasource.network.model.base.ApiWrapper
import m.tech.gapotest.framework.datasource.network.model.response.NewsFeedDto
import retrofit2.http.GET

interface NewsFeedApi {

    @GET(NEWS_FEED)
    suspend fun getNewsFeed(): ApiWrapper<List<NewsFeedDto>>

    //example of post body
//    @POST(POSTS)
//    suspend fun postDummies(
//        @Body fakeRequest: DummyFakeRequest
//    )

}