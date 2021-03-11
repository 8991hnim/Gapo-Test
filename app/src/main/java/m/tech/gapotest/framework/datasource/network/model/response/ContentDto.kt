package m.tech.gapotest.framework.datasource.network.model.response

import com.google.gson.annotations.SerializedName

data class ContentDto(
    @SerializedName("href")
    val href: String?,

    @SerializedName("preview_image")
    val previewImage: ImageDto?,

    @SerializedName("duration")
    val duration: Long?,

    @SerializedName("caption")
    val caption: String?
) {
}