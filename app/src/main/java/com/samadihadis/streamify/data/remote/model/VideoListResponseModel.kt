package com.samadihadis.streamify.data.remote.model

data class VideoListResponseModel(
    val page: Int,
    val per_page: Int,
    val videos: List<VideoModel>,
    val total_results: Int,
    val next_page: String,
    val url: String
)
