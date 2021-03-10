package m.tech.gapotest.framework.datasource.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import m.tech.gapotest.framework.datasource.cache.database.dao.DetailDocumentDao
import m.tech.gapotest.framework.datasource.cache.database.dao.DummyDao
import m.tech.gapotest.framework.datasource.cache.database.dao.NewsFeedDao
import m.tech.gapotest.framework.datasource.cache.model.DetailDocumentEntity
import m.tech.gapotest.framework.datasource.cache.model.DummyEntity
import m.tech.gapotest.framework.datasource.cache.model.NewsFeedEntity

@Database(
    entities = [
        DummyEntity::class,
        NewsFeedEntity::class,
        DetailDocumentEntity::class
    ],
    version = 1
)

@TypeConverters(DataConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dummyDao(): DummyDao
    abstract fun newsFeedDao(): NewsFeedDao
    abstract fun detailDocumentDao(): DetailDocumentDao

    companion object {
        const val DATABASE_NAME = "gapo_app_db"
    }
}