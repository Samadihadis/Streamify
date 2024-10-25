package com.samadihadis.streamify.presentation.video.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samadihadis.streamify.data.Constants
import com.samadihadis.streamify.data.MainVideoModel
import com.samadihadis.streamify.data.VideoRepository
import kotlinx.coroutines.launch

class VideoListViewModel(private val repository: VideoRepository) : ViewModel() {

    val videos: LiveData<List<MainVideoModel>> = repository.videos
    val loading: LiveData<Boolean> = repository.loading
    val error: LiveData<String> = repository.error


    fun fetchNatureVideos() = viewModelScope.launch {
        repository.fetchVideoList(Constants.VIDEO_CATEGORY)
    }
}
