package com.samadihadis.streamify.data.remote.model

import java.io.Serializable

data class VideoModel(
    val id: Int,
    val width: Int,
    val height: Int,
    val duration: Int,
    val full_res: String?,
    val tags: List<String>,
    val url: String,
    val image: String,
    val avg_color: String?,
    val user: UserModel,
    val video_files: List<VideoFileModel>,
    val video_pictures: List<VideoPictureModel>
): Serializable
