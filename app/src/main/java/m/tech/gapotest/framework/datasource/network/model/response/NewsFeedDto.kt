package m.tech.gapotest.framework.datasource.network.model.response

import com.google.gson.annotations.SerializedName

data class NewsFeedDto(
    @SerializedName("document_id")
    val documentId: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("published_date")
    val publishedDate: String,

    @SerializedName("origin_url")
    val originUrl: String,

    @SerializedName("publisher")
    val publisher: PublisherDto,

    @SerializedName("content_type")
    val contentType: String,

    @SerializedName("avatar")
    val avatar: ImageDto?,

    @SerializedName("images")
    val images: List<ImageDto>?,

    @SerializedName("content")
    val content: ContentDto?
) {

}