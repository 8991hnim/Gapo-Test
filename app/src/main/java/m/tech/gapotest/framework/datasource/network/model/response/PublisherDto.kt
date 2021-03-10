package m.tech.gapotest.framework.datasource.network.model.response

import com.google.gson.annotations.SerializedName

data class PublisherDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("icon")
    val icon: String
) {
}