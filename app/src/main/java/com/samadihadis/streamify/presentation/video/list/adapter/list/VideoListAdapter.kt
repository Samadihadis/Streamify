package com.samadihadis.streamify.presentation.video.list.adapter.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.samadihadis.streamify.R
import com.samadihadis.streamify.data.MainVideoModel

class VideoListAdapter(
    private val itemClicked: ((MainVideoModel) -> Unit)
) : RecyclerView.Adapter<VideoItemListViewHolder>() {

    private var videoList: MutableList<MainVideoModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoItemListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_video_list, parent, false)
        return VideoItemListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    fun addVideo(movieModel: MainVideoModel) {
        videoList.add(movieModel)
        notifyItemInserted(videoList.size - 1)
    }

    fun addVideoList(movieModelList: List<MainVideoModel>) {
        videoList.addAll(movieModelList)
        notifyItemRangeInserted(videoList.size - 1, movieModelList.size)
    }

    fun clearVideoList() {
        videoList.clear()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: VideoItemListViewHolder, position: Int) {
        holder.apply {
            titleTextView.text = videoList[position].userName
            descriptionTextView.text = videoList[position].userId
            Glide.with(holder.rootLayout.context)
                .load(videoList[position].imageUrl)
                .placeholder(R.drawable.banner_image_placeholder)
                .transform(CenterCrop(), RoundedCorners(40))
                .into(avatarImageView)
            rootLayout.setOnClickListener {
                itemClicked(videoList[position])
            }
        }
    }

}