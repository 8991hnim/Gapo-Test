package m.tech.gapotest.framework.datasource.cache.model

import androidx.room.ColumnInfo

data class ImageEntity(
    @ColumnInfo(name = "href")
    val href: String,

    @ColumnInfo(name = "mainColor")
    val mainColor: String,

    @ColumnInfo(name = "width")
    val width: Int,

    @ColumnInfo(name = "height")
    val height: Int
) {
}