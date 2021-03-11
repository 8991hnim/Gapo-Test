package m.tech.gapotest.framework.datasource.network.mappers

import m.tech.gapotest.business.domain.Content
import m.tech.gapotest.business.domain.Image
import m.tech.gapotest.framework.datasource.network.model.response.ContentDto
import m.tech.gapotest.framework.datasource.network.model.response.ImageDto

object DtoMapperUtil {
    fun toContent(model: ContentDto?): Content? {
        return if (model != null)
            Content(
                href = model.href,
                previewImage = toImage(model.previewImage),
                duration = model.duration,
                caption = model.caption,
            )
        else null
    }

    fun fromContent(domainModel: Content?): ContentDto? {
        return if (domainModel != null)
            ContentDto(
                href = domainModel.href ?: "",
                previewImage = fromImage(domainModel.previewImage),
                duration = domainModel.duration ?: 0,
                caption = domainModel.caption,
            )
        else null
    }

    fun fromImage(image: Image?): ImageDto? {
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

    fun toImage(entity: ImageDto?): Image? {
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