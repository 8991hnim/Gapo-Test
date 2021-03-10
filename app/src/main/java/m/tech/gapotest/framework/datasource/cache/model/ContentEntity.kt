package m.tech.gapotest.framework.datasource.cache.model

import androidx.room.ColumnInfo
import androidx.room.Embedded

data class ContentEntity(
    @ColumnInfo(name = "href")
    val href: String,

    @Embedded(prefix = "preview_img_")
    val previewImage: ImageEntity,

    @ColumnInfo(name = "duration")
    val duration: Long,

    @ColumnInfo(name = "caption")
    val caption: String?
) {
}