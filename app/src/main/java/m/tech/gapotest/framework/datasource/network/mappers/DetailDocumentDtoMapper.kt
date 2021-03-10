package m.tech.gapotest.framework.datasource.network.mappers

import m.tech.gapotest.business.domain.*
import m.tech.gapotest.framework.datasource.DomainMapper
import m.tech.gapotest.framework.datasource.cache.model.*
import m.tech.gapotest.framework.datasource.network.model.response.DetailDocumentDto
import m.tech.gapotest.framework.datasource.network.model.response.PublisherDto
import javax.inject.Inject

class DetailDocumentDtoMapper
@Inject
constructor() : DomainMapper<DetailDocumentDto, DetailDocument> {

    override fun toDomain(model: DetailDocumentDto): DetailDocument {
        return DetailDocument(
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
            templateType = model.templateType
        )
    }

    override fun fromDomain(domainModel: DetailDocument): DetailDocumentDto {
        return DetailDocumentDto(
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
            templateType = domainModel.templateType
        )
    }

    fun toDomainList(list: List<DetailDocumentDto>): List<DetailDocument> = list.map {
        toDomain(it)
    }

    fun fromDomainList(list: List<DetailDocument>): List<DetailDocumentDto> = list.map {
        fromDomain(it)
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