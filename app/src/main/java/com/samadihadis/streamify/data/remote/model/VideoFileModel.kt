package com.samadihadis.streamify.data.remote.model

import java.io.Serializable


data class VideoFileModel(
    val id: Int,
    val quality: String,
    val file_type: String,
    val width: Int,
    val height: Int,
    val fps: Double,
    val link: String,
    val size: Int
): Serializable {

    fun getSizeDescription() = (size.toDouble() / 1000000).toInt().toString() + " MB"
}
