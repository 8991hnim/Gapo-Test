package m.tech.gapotest.business.domain

data class DetailDocument(
    val baseDocument: BaseDocument,
    val templateType: String,
    val section: List<Section>
){
}