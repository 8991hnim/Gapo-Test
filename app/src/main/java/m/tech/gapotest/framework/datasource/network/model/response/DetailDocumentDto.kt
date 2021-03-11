package m.tech.gapotest.framework.datasource.network.model.response

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "detail_document")
data class DetailDocumentDto(

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

    @SerializedName("template_type")
    val templateType: String,

    @SerializedName("sections")
    val sections: List<SectionDto>
) {
}