package m.tech.gapotest.framework.datasource.network.mappers

import m.tech.gapotest.business.domain.*
import m.tech.gapotest.framework.datasource.DomainMapper
import m.tech.gapotest.framework.datasource.cache.model.*
import m.tech.gapotest.framework.datasource.network.model.response.ContentDto
import m.tech.gapotest.framework.datasource.network.model.response.ImageDto
import m.tech.gapotest.framework.datasource.network.model.response.NewsFeedDto
import m.tech.gapotest.framework.datasource.network.model.response.PublisherDto
import javax.inject.Inject

class NewsFeedDtoMapper
@Inject
constructor() : DomainMapper<NewsFeedDto, NewsFeed> {

    override fun toDomain(model: NewsFeedDto): NewsFeed {
        return NewsFeed(
            BaseDocument(
                documentId = model.documentId,
                title = model.title,
                description = model.description,
                publishedDate = model.publishedDate,
                originUrl = model.originUrl,
                publisher = Publisher(
                    id = model.publisher.id,
                    name = model.publisher.name,
                    icon = model.publisher.icon
                )
            ),
            contentType = model.contentType,
            avatar = toImage(model.avatar),
            images = model.images?.map { toImage(it)!! },
            content = toContent(model.content)
        )
    }

    override fun fromDomain(domainModel: NewsFeed): NewsFeedDto {
        return NewsFeedDto(
            documentId = domainModel.baseDocument.documentId,
            title = domainModel.baseDocument.title,
            description = domainModel.baseDocument.description,
            publishedDate = domainModel.baseDocument.publishedDate,
            originUrl = domainModel.baseDocument.originUrl,
            publisher = PublisherDto(
                id = domainModel.baseDocument.publisher.id,
                name = domainModel.baseDocument.publisher.name,
                icon = domainModel.baseDocument.publisher.icon
            ),
            contentType = domainModel.contentType,
            avatar = fromImage(domainModel.avatar),
            images = domainModel.images?.map { fromImage(it)!! },
            content = fromContent(domainModel.content)
        )
    }

    fun toDomainList(list: List<NewsFeedDto>): List<NewsFeed> = list.map {
        toDomain(it)
    }

    fun fromDomainList(list: List<NewsFeed>): List<NewsFeedDto> = list.map {
        fromDomain(it)
    }

    private fun toContent(model: ContentDto?): Content? {
        return if (model != null)
            Content(
                href = model.href,
                previewImage = toImage(model.previewImage)!!,
                duration = model.duration,
                caption = model.caption,
            )
        else null
    }

    private fun fromContent(domainModel: Content?): ContentDto? {
        return if (domainModel != null)
            ContentDto(
                href = domainModel.href,
                previewImage = fromImage(domainModel.previewImage)!!,
                duration = domainModel.duration,
                caption = domainModel.caption,
            )
        else null
    }

    private fun fromImage(image: Image?): ImageDto? {
        return if (image != null)
            ImageDto(
                href = image.href,
                mainColor = image.mainColor,
                width = image.width,
                height = image.height,
            )
        else
            null
    }

    private fun toImage(entity: ImageDto?): Image? {
        return if (entity != null)
            Image(
                href = entity.href,
                mainColor = entity.mainColor ?: "#FFFFFF",
                width = entity.width,
                height = entity.height,
            )
        else
            null
    }

}