package com.samadihadis.streamify.data

import com.samadihadis.streamify.data.local.model.VideoEntity
import com.samadihadis.streamify.data.remote.model.VideoModel

object DataModelMapper {

    fun VideoEntity.toMainModel() = MainVideoModel(
        vID = vID,
        userName = userName,
        userId = userId,
        width = width,
        height = height,
        duration = duration,
        quality = quality,
        imageUrl = imageUrl,
        size = size,
        url = url,
        localPath = localPath,
        isFavorite = isFavorite,
    )

    fun VideoModel.toEntityModel() = VideoEntity(
        vID = id,
        userName = user.name,
        userId = user.getUserName(),
        width = width,
        height = height,
        duration = duration,
        quality = video_files.firstOrNull()?.quality?.uppercase().orEmpty(),
        imageUrl = image,
        size = video_files.firstOrNull()?.getSizeDescription().orEmpty(),
        url = url,
        localPath = "",
        isFavorite = false
    )

    fun VideoModel.toMainModel() = MainVideoModel(
        vID = id,
        userName = user.name,
        userId = user.getUserName(),
        width = width,
        height = height,
        duration = duration,
        quality = video_files.firstOrNull()?.quality?.uppercase().orEmpty(),
        imageUrl = image,
        size = video_files.firstOrNull()?.getSizeDescription().orEmpty(),
        url = video_files.firstOrNull()?.link.orEmpty(),
        localPath = "",
        isFavorite = false,
    )

}