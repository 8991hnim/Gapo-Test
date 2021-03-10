package m.tech.gapotest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import m.tech.gapotest.business.data.network.abstraction.DummyNetworkDataSource
import m.tech.gapotest.framework.datasource.network.api.DummyApi
import m.tech.gapotest.framework.datasource.network.implementation.DummyNetworkImpl
import m.tech.gapotest.framework.datasource.network.mappers.DummyDtoMapper

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

}