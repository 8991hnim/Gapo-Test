package m.tech.gapotest.framework.datasource.cache.mappers

import android.annotation.SuppressLint
import m.tech.gapotest.business.domain.BaseDocument
import m.tech.gapotest.business.domain.NewsFeed
import m.tech.gapotest.business.domain.Publisher
import m.tech.gapotest.framework.datasource.DomainMapper
import m.tech.gapotest.framework.datasource.cache.mappers.CacheMapperUtil.fromContent
import m.tech.gapotest.framework.datasource.cache.mappers.CacheMapperUtil.fromImage
import m.tech.gapotest.framework.datasource.cache.mappers.CacheMapperUtil.toContent
import m.tech.gapotest.framework.datasource.cache.mappers.CacheMapperUtil.toImage
import m.tech.gapotest.framework.datasource.cache.model.BaseDocumentEntity
import m.tech.gapotest.framework.datasource.cache.model.NewsFeedEntity
import m.tech.gapotest.framework.datasource.cache.model.PublisherEntity
import java.text.SimpleDateFormat
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

}