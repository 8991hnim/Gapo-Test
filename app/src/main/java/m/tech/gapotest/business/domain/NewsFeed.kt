package m.tech.gapotest.business.domain

data class NewsFeed(
    val baseDocument: BaseDocument,
    val contentType: String,
    val avatar: Image?,
    val images: List<Image>?,
    val content: Content?
) {

    companion object{
        const val CONTENT_TYPE_GALLERY = "gallery"
        const val CONTENT_TYPE_VIDEO = "video"
        const val CONTENT_TYPE_ARTICLE = "article"
        const val CONTENT_TYPE_STORY = "story"
        const val CONTENT_TYPE_LONG_FORM = "long_form"
    }

}