package m.tech.gapotest.framework.datasource.cache.database.dao

import androidx.room.*
import m.tech.gapotest.framework.datasource.cache.model.DummyEntity
import m.tech.gapotest.framework.datasource.cache.model.DummyEntity.Companion.ID
import m.tech.gapotest.framework.datasource.cache.model.DummyEntity.Companion.TABLE_NAME
import m.tech.gapotest.framework.datasource.cache.model.NewsFeedEntity

@Dao
interface NewsFeedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewsFeed(newsFeedEntity: NewsFeedEntity)

    @Update
    suspend fun updateNewsFeed(newsFeedEntity: NewsFeedEntity)

    @Delete
    suspend fun deleteNewsFeed(newsFeedEntity: NewsFeedEntity)

    @Query("SELECT * FROM news_feed ORDER BY published_date DESC, title ASC")
    suspend fun getNewsFeed(): List<NewsFeedEntity>

    @Query("SELECT * FROM news_feed ORDER BY published_date DESC, title ASC LIMIT :page * :limit")
    suspend fun getNewsFeed(page: Int, limit: Int): List<NewsFeedEntity>

    @Query("SELECT * FROM news_feed WHERE document_id = :id")
    suspend fun getNewsFeedById(id: String): NewsFeedEntity?

}
