package m.tech.gapotest.framework.datasource.cache.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.PrimaryKey

data class BaseDocumentEntity(

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "published_date")
    val publishedDate: Long,

    @ColumnInfo(name = "origin_url")
    val originUrl: String,

    @Embedded(prefix = "pub_")
    val publisher: PublisherEntity
) {
}