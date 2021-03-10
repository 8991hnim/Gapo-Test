package m.tech.gapotest.framework.datasource.network.model.response

import com.google.gson.annotations.SerializedName

data class ImageDto(
    @SerializedName("href")
    val href: String,

    @SerializedName("mainColor")
    val mainColor: String? = "#FFFFFF",

    @SerializedName("width")
    val width: Int,

    @SerializedName("height")
    val height: Int
) {
}