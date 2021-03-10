package m.tech.gapotest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import m.tech.gapotest.business.data.network.abstraction.DetailNetworkDataSource
import m.tech.gapotest.business.data.network.abstraction.DummyNetworkDataSource
import m.tech.gapotest.business.data.network.abstraction.NewsFeedNetworkDataSource
import m.tech.gapotest.framework.datasource.network.api.DetailApi
import m.tech.gapotest.framework.datasource.network.api.DummyApi
import m.tech.gapotest.framework.datasource.network.api.NewsFeedApi
import m.tech.gapotest.framework.datasource.network.implementation.DetailNetworkImpl
import m.tech.gapotest.framework.datasource.network.implementation.DummyNetworkImpl
import m.tech.gapotest.framework.datasource.network.implementation.NewsFeedNetworkImpl
import m.tech.gapotest.framework.datasource.network.mappers.DetailDocumentDtoMapper
import m.tech.gapotest.framework.datasource.network.mappers.DummyDtoMapper
import m.tech.gapotest.framework.datasource.network.mappers.NewsFeedDtoMapper

@InstallIn(ApplicationComponent::class)
@Module
object NetworkImplModule {

    @Provides
    fun provideDummyNetworkDataSource(
        dummyApi: DummyApi,
        dummyDtoMapper: DummyDtoMapper
    ): DummyNetworkDataSource =
        DummyNetworkImpl(
            dummyApi, dummyDtoMapper
        )

    @Provides
    fun provideNewsFeedNetworkDataSource(
        newsFeedApi: NewsFeedApi,
        mapper: NewsFeedDtoMapper
    ): NewsFeedNetworkDataSource = NewsFeedNetworkImpl(
        newsFeedApi,
        mapper
    )

    @Provides
    fun provideDetailDocumentNetworkDataSource(
        detailApi: DetailApi,
        mapper: DetailDocumentDtoMapper
    ): DetailNetworkDataSource = DetailNetworkImpl(
        detailApi,
        mapper
    )
}
