package m.tech.gapotest.framework.datasource.cache.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_feed")
data class NewsFeedEntity(

    @PrimaryKey
    @ColumnInfo(name = "document_id")
    val documentId: String,

    @Embedded
    val baseDocument: BaseDocumentEntity,

    @ColumnInfo(name = "content_type")
    val contentType: String,

    @Embedded(prefix = "ava_")
    val avatar: ImageEntity?,

    @ColumnInfo(name = "images")
    val images: List<ImageEntity>?,

    @Embedded(prefix = "content_")
    val content: ContentEntity?
) {

}