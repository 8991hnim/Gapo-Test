package m.tech.gapotest.framework.datasource.network.model.response

import com.google.gson.annotations.SerializedName

data class SectionDto(
    @SerializedName("section_type")
    val sectionType: Int,

    @SerializedName("content")
    val content: ContentDto,
) {
}