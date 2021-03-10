package m.tech.gapotest.framework.datasource.cache.mappers

import android.annotation.SuppressLint
import m.tech.gapotest.business.domain.*
import m.tech.gapotest.framework.datasource.DomainMapper
import m.tech.gapotest.framework.datasource.cache.model.*
import java.text.SimpleDateFormat
import javax.inject.Inject

class DetailDocumentEntityMapper
@Inject
constructor() : DomainMapper<DetailDocumentEntity, DetailDocument> {

    @SuppressLint("SimpleDateFormat")
    private val sdf = SimpleDateFormat("yyyy-mm-dd hh:mm:ss")

    override fun toDomain(model: DetailDocumentEntity): DetailDocument {
        return DetailDocument(
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
            templateType = model.templateType
        )
    }

    override fun fromDomain(domainModel: DetailDocument): DetailDocumentEntity {
        return DetailDocumentEntity(
            documentId = domainModel.baseDocument.documentId,
            BaseDocumentEntity(
                title = domainModel.baseDocument.title,
                description = domainModel.baseDocument.description,
                publishedDate = sdf.parse(
                    domainModel.baseDocument.publishedDate.replace("T", " ").replace("Z", " ")
                )?.time ?: 0,
                originUrl = domainModel.baseDocument.originUrl,
                publisher = PublisherEntity(
                    id = domainModel.baseDocument.publisher.id,
                    name = domainModel.baseDocument.publisher.name,
                    icon = domainModel.baseDocument.publisher.icon
                )
            ),
            templateType = domainModel.templateType
        )
    }

    fun toDomainList(list: List<DetailDocumentEntity>): List<DetailDocument> = list.map {
        toDomain(it)
    }

    fun fromDomainList(list: List<DetailDocument>): List<DetailDocumentEntity> = list.map {
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