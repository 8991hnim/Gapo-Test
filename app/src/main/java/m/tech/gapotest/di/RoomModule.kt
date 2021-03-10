package m.tech.gapotest.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import m.tech.gapotest.framework.datasource.cache.database.AppDatabase
import m.tech.gapotest.framework.datasource.cache.database.dao.DetailDocumentDao
import m.tech.gapotest.framework.datasource.cache.database.dao.DummyDao
import m.tech.gapotest.framework.datasource.cache.database.dao.NewsFeedDao

@InstallIn(ApplicationComponent::class)
@Module
object RoomModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration() //TODO: change this
            .build()
    }

    @Provides
    fun provideDummyDao(db: AppDatabase): DummyDao {
        return db.dummyDao()
    }

    @Provides
    fun provideNewsFeedDao(db: AppDatabase): NewsFeedDao {
        return db.newsFeedDao()
    }

    @Provides
    fun provideDetailDocumentDao(db: AppDatabase): DetailDocumentDao {
        return db.detailDocumentDao()
    }

}