package m.tech.gapotest.framework.datasource.network.mappers

import m.tech.gapotest.business.domain.BaseDocument
import m.tech.gapotest.business.domain.DetailDocument
import m.tech.gapotest.business.domain.Publisher
import m.tech.gapotest.business.domain.Section
import m.tech.gapotest.framework.datasource.DomainMapper
import m.tech.gapotest.framework.datasource.network.mappers.DtoMapperUtil.fromContent
import m.tech.gapotest.framework.datasource.network.mappers.DtoMapperUtil.toContent
import m.tech.gapotest.framework.datasource.network.model.response.DetailDocumentDto
import m.tech.gapotest.framework.datasource.network.model.response.PublisherDto
import m.tech.gapotest.framework.datasource.network.model.response.SectionDto
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
            templateType = model.templateType,
            section = model.sections.map { Section(it.sectionType, toContent(it.content)!!) }
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
            templateType = domainModel.templateType,
            sections = domainModel.section.map {
                SectionDto(
                    it.sectionType,
                    fromContent(it.content)!!
                )
            }
        )
    }

    fun toDomainList(list: List<DetailDocumentDto>): List<DetailDocument> = list.map {
        toDomain(it)
    }

    fun fromDomainList(list: List<DetailDocument>): List<DetailDocumentDto> = list.map {
        fromDomain(it)
    }


}