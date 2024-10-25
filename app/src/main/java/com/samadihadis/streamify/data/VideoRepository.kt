package com.samadihadis.streamify.data

import androidx.lifecycle.LiveData
import com.samadihadis.streamify.data.DataModelMapper.toEntityModel
import com.samadihadis.streamify.data.DataModelMapper.toMainModel
import com.samadihadis.streamify.data.local.VideoDao
import com.samadihadis.streamify.data.remote.ApiService
import com.samadihadis.streamify.utils.LiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideoRepository(private val videoDao: VideoDao, private val apiService: ApiService) {

    private val _videos = LiveEvent<List<MainVideoModel>>()
    val videos: LiveData<List<MainVideoModel>> get() = _videos

    private val _loading = LiveEvent<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _error = LiveEvent<String>()
    val error: LiveData<String> get() = _error

    suspend fun fetchVideoList(category: String) {
        try {
            _loading.value = true
            val videoList = apiService.getVideos(
                query = category,
                perPage = Constants.PER_PAGE_SIZE
            ).videos
            withContext(Dispatchers.IO) {
                videoDao.insert(videoList.map { it.toEntityModel() })
            }
            _videos.value = videoList.map { it.toMainModel() }
            _loading.value = false
        } catch (e: Exception) {
            _error.value = e.message
            val cachedVideos = withContext(Dispatchers.IO) {
                videoDao.getAll()
            }
            _videos.value = cachedVideos.map { it.toMainModel() }
            _loading.value = false
        }
    }

}
