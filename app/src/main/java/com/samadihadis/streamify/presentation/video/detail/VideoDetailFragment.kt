package com.samadihadis.streamify.presentation.video.detail

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.samadihadis.streamify.R
import com.samadihadis.streamify.databinding.FragmentVideoDetailBinding

class VideoDetailFragment : Fragment() {

    private val args by navArgs<VideoDetailFragmentArgs>()
    private lateinit var binding: FragmentVideoDetailBinding
    private lateinit var viewModel: VideoDetailViewModel
    private lateinit var exoPlayer: ExoPlayer

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentVideoDetailBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[VideoDetailViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupVideoPlayer()
    }

    private fun setupViews() = with(binding) {
        Glide.with(requireContext())
            .load(args.videoModel.imageUrl)
            .placeholder(R.drawable.banner_image_placeholder)
            .circleCrop()
            .into(videoImageView)

        nameValueTextView.text = args.videoModel.userName
        identifierValueTextView.text = args.videoModel.userId
        qualityValueTextView.text = args.videoModel.quality
        sizeValueTextView.text = args.videoModel.size
        durationValueTextView.text = "${args.videoModel.duration} s"
    }

    private fun setupVideoPlayer() {
        exoPlayer = ExoPlayer.Builder(requireContext()).build()
        binding.playerView.player = exoPlayer
        val mediaItem = MediaItem.fromUri(Uri.parse(args.videoModel.url))
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
    }

    override fun onPause() {
        super.onPause()
        exoPlayer.pause()
    }

    override fun onStop() {
        super.onStop()
        exoPlayer.release()
    }

}