package m.tech.gapotest.framework.datasource.cache.mappers

import m.tech.gapotest.business.domain.Content
import m.tech.gapotest.business.domain.Image
import m.tech.gapotest.framework.datasource.cache.model.ContentEntity
import m.tech.gapotest.framework.datasource.cache.model.ImageEntity

object CacheMapperUtil {
    fun toContent(entity: ContentEntity?): Content? {
        return if (entity != null)
            Content(
                href = entity.href,
                previewImage = toImage(entity.previewImage),
                duration = entity.duration,
                caption = entity.caption,
            )
        else null
    }

    fun fromContent(domainModel: Content?): ContentEntity? {
        return if (domainModel != null)
            ContentEntity(
                href = domainModel.href ?: "",
                previewImage = fromImage(domainModel.previewImage) ?: ImageEntity("", "", 0, 0),
                duration = domainModel.duration ?: 0,
                caption = domainModel.caption ?: "",
            )
        else null
    }

    fun fromImage(image: Image?): ImageEntity? {
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

    fun toImage(entity: ImageEntity?): Image? {
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