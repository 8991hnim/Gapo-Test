package m.tech.gapotest.framework.datasource.cache.mappers

import android.annotation.SuppressLint
import android.util.Log
import m.tech.gapotest.business.domain.*
import m.tech.gapotest.framework.datasource.DomainMapper
import m.tech.gapotest.framework.datasource.cache.model.*
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class NewsFeedEntityMapper
@Inject
constructor() : DomainMapper<NewsFeedEntity, NewsFeed> {

    @SuppressLint("SimpleDateFormat")
    override fun toDomain(model: NewsFeedEntity): NewsFeed {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

        return NewsFeed(
            BaseDocument(
                documentId = model.documentId,
                title = model.baseDocument.title,
                description = model.baseDocument.description,
                publishedDate = sdf.format(model.baseDocument.publishedDate),
                originUrl = model.baseDocument.originUrl,
                publisher = Publisher(
                    id = model.baseDocument.publisher.id,
                    name = model.baseDocument.publisher.name,
                    icon = model.baseDocument.publisher.icon
                )
            ),
            contentType = model.contentType,
            avatar = toImage(model.avatar),
            images = model.images?.map { toImage(it)!! },
            content = toContent(model.content)
        )
    }

    @SuppressLint("SimpleDateFormat")
    override fun fromDomain(domainModel: NewsFeed): NewsFeedEntity {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

        return NewsFeedEntity(
            documentId = domainModel.baseDocument.documentId,
            BaseDocumentEntity(
                title = domainModel.baseDocument.title,
                description = domainModel.baseDocument.description,
                publishedDate = sdf.parse(domainModel.baseDocument.publishedDate)?.time ?: 0,
                originUrl = domainModel.baseDocument.originUrl,
                publisher = PublisherEntity(
                    id = domainModel.baseDocument.publisher.id,
                    name = domainModel.baseDocument.publisher.name,
                    icon = domainModel.baseDocument.publisher.icon
                )
            ),
            contentType = domainModel.contentType,
            avatar = fromImage(domainModel.avatar),
            images = domainModel.images?.map { fromImage(it)!! },
            content = fromContent(domainModel.content)
        )
    }

    fun toDomainList(list: List<NewsFeedEntity>): List<NewsFeed> = list.map {
        toDomain(it)
    }

    fun fromDomainList(list: List<NewsFeed>): List<NewsFeedEntity> = list.map {
        fromDomain(it)
    }

    private fun toContent(entity: ContentEntity?): Content? {
        return if (entity != null)
            Content(
                href = entity.href,
                previewImage = toImage(entity.previewImage)!!,
                duration = entity.duration,
                caption = entity.caption,
            )
        else null
    }

    private fun fromContent(domainModel: Content?): ContentEntity? {
        return if (domainModel != null)
            ContentEntity(
                href = domainModel.href,
                previewImage = fromImage(domainModel.previewImage)!!,
                duration = domainModel.duration,
                caption = domainModel.caption,
            )
        else null
    }

    private fun fromImage(image: Image?): ImageEntity? {
        return if (image != null)
            ImageEntity(
                href = image.href,
                mainColor = image.mainColor,
                width = image.width,
                height = image.height,
            )
        else
            null
    }

    private fun toImage(entity: ImageEntity?): Image? {
        return if (entity != null)
            Image(
                href = entity.href,
                mainColor = entity.mainColor,
                width = entity.width,
                height = entity.height,
            )
        else
            null
    }

}