package com.samadihadis.streamify.presentation.video.list

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.samadihadis.streamify.R
import com.samadihadis.streamify.data.MainVideoModel
import com.samadihadis.streamify.data.VideoRepository
import com.samadihadis.streamify.data.local.VideoRoomDatabase
import com.samadihadis.streamify.data.remote.RetrofitInstance
import com.samadihadis.streamify.databinding.FragmentVideoListBinding
import com.samadihadis.streamify.presentation.video.list.adapter.grid.VideoGridAdapter
import com.samadihadis.streamify.presentation.video.list.adapter.list.VideoListAdapter
import com.samadihadis.streamify.utils.gone
import com.samadihadis.streamify.utils.visible

class VideoListFragment : Fragment() {

    private lateinit var binding: FragmentVideoListBinding
    private lateinit var viewModel: VideoListViewModel
    private val videoListAdapter: VideoListAdapter by lazy {
        VideoListAdapter() {
            onItemClicked(it)
        }
    }
    private val videoGridAdapter: VideoGridAdapter by lazy {
        VideoGridAdapter() {
            onItemClicked(it)
        }
    }
    private val decoration: DividerItemDecoration by lazy {
        DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
    }
    private var isGrid: Boolean = false
    private var animation: ObjectAnimator? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentVideoListBinding.inflate(inflater, container, false)
        val videoDao = VideoRoomDatabase.getDatabase(requireContext()).videoDao()
        val apiService = RetrofitInstance.apiService
        val repository = VideoRepository(videoDao, apiService)
        viewModel = ViewModelProvider(this, VideoListViewModelFactory(repository))[VideoListViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cleanList()
        initLoadingAnimator()
        observeLoading()
        observeError()
        observeVideoListResponse()
        setupViews()
        setupListAdapter()
        // At this time we fetch the nature video category from Pexels api service
        viewModel.fetchNatureVideos()
    }

    private fun setupViews() = with(binding) {
        retryButton.setOnClickListener {
            viewModel.fetchNatureVideos()
        }
        fabButton.setOnClickListener {
            cleanList()
            changeFabState()
            changeAdapter()
        }
    }

    private fun setupListAdapter() = with(binding.videoRecyclerView) {
        layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        addItemDecoration(decoration)
        adapter = videoListAdapter
    }

    private fun setupGridAdapter() = with(binding.videoRecyclerView) {
        layoutManager = GridLayoutManager(requireContext(), 2)
        removeItemDecoration(decoration)
        adapter = videoGridAdapter
    }

    private fun changeAdapter() {
        if (isGrid) {
            setupGridAdapter()
            viewModel.videos.value?.let {
                videoGridAdapter.addVideoList(it)
            }
        } else {
            setupListAdapter()
            viewModel.videos.value?.let {
                videoListAdapter.addVideoList(it)
            }
        }
    }

    private fun changeFabState() = with(binding) {
        if (!isGrid) {
            fabButton.setImageResource(R.drawable.baseline_format_list)
            isGrid = true
        } else {
            fabButton.setImageResource(R.drawable.baseline_grid_view)
            isGrid = false
        }
    }

    private fun cleanList() {
        videoListAdapter.clearVideoList()
        videoGridAdapter.clearVideoList()
    }

    private fun observeVideoListResponse() = viewModel.videos.observe(viewLifecycleOwner) {
        videoListAdapter.addVideoList(it)
    }

    private fun observeError() = viewModel.error.observe(viewLifecycleOwner) {
        Toast.makeText(requireContext(), "An error occurred in API service call, load data from database..", Toast.LENGTH_SHORT).show()
    }

    private fun observeLoading() = viewModel.loading.observe(viewLifecycleOwner) {
        if (it) showLoading() else hideLoading()
    }

    private fun onItemClicked(model: MainVideoModel) {
        findNavController().navigate(VideoListFragmentDirections.actionToVideoDetailFragment(model))
    }

    private fun initLoadingAnimator() {
        animation = ObjectAnimator.ofFloat(binding.progressBarLoading, "rotation", 0f, 360f)
        animation?.duration = 1000
        animation?.repeatCount = ObjectAnimator.INFINITE
        animation?.interpolator = LinearInterpolator()
    }

    private fun showLoading() {
        with(binding) {
            progressBarLoading.visible()
            videoRecyclerView.gone()
            retryButton.gone()
            errorTextView.gone()
        }
        animation?.start()
    }

    private fun hideLoading() {
        animation?.cancel()
        with(binding) {
            progressBarLoading.gone()
            videoRecyclerView.visible()
        }
    }

    private fun showErrorState(message: String) = with(binding) {
        progressBarLoading.gone()
        videoRecyclerView.gone()
        retryButton.visible()
        errorTextView.visible()
        errorTextView.text = message
    }

    class VideoListViewModelFactory(private val repository: VideoRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(VideoListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return VideoListViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}