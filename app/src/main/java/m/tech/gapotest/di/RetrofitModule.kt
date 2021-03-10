package m.tech.gapotest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import m.tech.gapotest.framework.datasource.network.api.DetailApi
import m.tech.gapotest.framework.datasource.network.api.DummyApi
import m.tech.gapotest.framework.datasource.network.api.NetworkConstants.BASE_URL
import m.tech.gapotest.framework.datasource.network.api.NewsFeedApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(ApplicationComponent::class)
@Module
object RetrofitModule {

    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideDummyApi(retrofit: Retrofit): DummyApi =
        retrofit.create(DummyApi::class.java)

    @Provides
    fun provideNewsFeedApi(retrofit: Retrofit): NewsFeedApi =
        retrofit.create(NewsFeedApi::class.java)

    @Provides
    fun provideDetailApi(retrofit: Retrofit): DetailApi =
        retrofit.create(DetailApi::class.java)


}