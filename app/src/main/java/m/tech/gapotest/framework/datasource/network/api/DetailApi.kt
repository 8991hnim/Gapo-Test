package m.tech.gapotest.framework.datasource.network.api

import m.tech.gapotest.framework.datasource.network.api.NetworkConstants.DETAIL
import m.tech.gapotest.framework.datasource.network.model.response.DetailDocumentDto
import retrofit2.http.GET

interface DetailApi {

    @GET(DETAIL)
    suspend fun getNewsFeed(): DetailDocumentDto

    //example of post body
//    @POST(POSTS)
//    suspend fun postDummies(
//        @Body fakeRequest: DummyFakeRequest
//    )

}