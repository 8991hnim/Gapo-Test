package m.tech.gapotest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import m.tech.gapotest.business.data.cache.abstraction.DummyCacheDataSource
import m.tech.gapotest.framework.datasource.cache.database.dao.DummyDao
import m.tech.gapotest.framework.datasource.cache.implementation.DummyCacheImpl
import m.tech.gapotest.framework.datasource.cache.mappers.DummyEntityMapper

@InstallIn(ApplicationComponent::class)
@Module
object CacheImplModule {

    @Provides
    fun provideDummyCacheDataSource(
        dummyDao: DummyDao,
        dummyEntityMapper: DummyEntityMapper
    ): DummyCacheDataSource = DummyCacheImpl(
        dummyDao,
        dummyEntityMapper
    )


}