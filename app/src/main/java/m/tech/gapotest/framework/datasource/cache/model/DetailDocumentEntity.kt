package m.tech.gapotest.framework.datasource.cache.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detail_document")
data class DetailDocumentEntity(
    @PrimaryKey
    @ColumnInfo(name = "document_id")
    val documentId: String,

    @Embedded
    val baseDocument: BaseDocumentEntity,

    @ColumnInfo(name = "template_type")
    val templateType: String,
){
}