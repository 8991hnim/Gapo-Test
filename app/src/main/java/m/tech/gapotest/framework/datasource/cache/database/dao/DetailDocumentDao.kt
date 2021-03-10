package m.tech.gapotest.framework.datasource.cache.database.dao

import androidx.room.*
import m.tech.gapotest.framework.datasource.cache.model.DetailDocumentEntity

@Dao
interface DetailDocumentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDetail(detailDocumentEntity: DetailDocumentEntity)

    @Update
    suspend fun updateDetail(detailDocumentEntity: DetailDocumentEntity)

    @Query("SELECT * FROM detail_document WHERE document_id = :id")
    suspend fun getDetailById(id: String): DetailDocumentEntity?

}
