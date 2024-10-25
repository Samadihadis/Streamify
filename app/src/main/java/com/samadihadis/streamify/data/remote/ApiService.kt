package com.samadihadis.streamify.data.remote

import com.samadihadis.streamify.data.Constants
import com.samadihadis.streamify.data.remote.model.VideoListResponseModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers("Authorization: ${Constants.API_KEY}")
    @GET("videos/search")
    suspend fun getVideos(
        @Query("query") query: String, // category query of video list
        @Query("per_page") perPage: Int // per page video list size
    ): VideoListResponseModel

}