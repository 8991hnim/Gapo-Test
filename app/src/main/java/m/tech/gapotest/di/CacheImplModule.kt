package m.tech.gapotest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import m.tech.gapotest.business.data.cache.abstraction.DetailCacheDataSource
import m.tech.gapotest.business.data.cache.abstraction.DummyCacheDataSource
import m.tech.gapotest.business.data.cache.abstraction.NewsFeedCacheDataSource
import m.tech.gapotest.framework.datasource.cache.database.dao.DetailDocumentDao
import m.tech.gapotest.framework.datasource.cache.database.dao.DummyDao
import m.tech.gapotest.framework.datasource.cache.database.dao.NewsFeedDao
import m.tech.gapotest.framework.datasource.cache.implementation.DetailDocumentCacheImpl
import m.tech.gapotest.framework.datasource.cache.implementation.DummyCacheImpl
import m.tech.gapotest.framework.datasource.cache.implementation.NewsFeedCacheImpl
import m.tech.gapotest.framework.datasource.cache.mappers.DetailDocumentEntityMapper
import m.tech.gapotest.framework.datasource.cache.mappers.DummyEntityMapper
import m.tech.gapotest.framework.datasource.cache.mappers.NewsFeedEntityMapper

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

    @Provides
    fun provideNewsFeedCacheDataSource(
        newsFeedDao: NewsFeedDao,
        mapper: NewsFeedEntityMapper
    ): NewsFeedCacheDataSource = NewsFeedCacheImpl(
        newsFeedDao,
        mapper
    )

    @Provides
    fun provideDetailDocumentCacheDataSource(
        detailDocumentDao: DetailDocumentDao,
        mapper: DetailDocumentEntityMapper
    ): DetailCacheDataSource = DetailDocumentCacheImpl(
        detailDocumentDao,
        mapper
    )

}
