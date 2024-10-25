package com.samadihadis.streamify.presentation.video.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.samadihadis.streamify.databinding.FragmentVideoListBinding

class VideoListFragment : Fragment() {

    private lateinit var binding: FragmentVideoListBinding
    private lateinit var viewModel: VideoListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentVideoListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[VideoListViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() = with(binding) {

    }

}