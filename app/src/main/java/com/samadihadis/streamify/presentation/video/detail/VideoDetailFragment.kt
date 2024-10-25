package com.samadihadis.streamify.presentation.video.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.samadihadis.streamify.databinding.FragmentVideoDetailBinding

class VideoDetailFragment : Fragment() {

    private lateinit var binding: FragmentVideoDetailBinding
    private lateinit var viewModel: VideoDetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentVideoDetailBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[VideoDetailViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() = with(binding) {

    }

}