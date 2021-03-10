package m.tech.gapotest.business.domain

data class BaseDocument(
    val documentId: String,
    val title: String,
    val description: String,
    val publishedDate: String,
    val originUrl: String,
    val publisher: Publisher
) {
}