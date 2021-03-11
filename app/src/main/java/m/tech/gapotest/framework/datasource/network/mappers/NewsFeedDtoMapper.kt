package m.tech.gapotest.framework.datasource.network.mappers

import m.tech.gapotest.business.domain.*
import m.tech.gapotest.framework.datasource.DomainMapper
import m.tech.gapotest.framework.datasource.network.mappers.DtoMapperUtil.fromContent
import m.tech.gapotest.framework.datasource.network.mappers.DtoMapperUtil.fromImage
import m.tech.gapotest.framework.datasource.network.mappers.DtoMapperUtil.toContent
import m.tech.gapotest.framework.datasource.network.mappers.DtoMapperUtil.toImage
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


}