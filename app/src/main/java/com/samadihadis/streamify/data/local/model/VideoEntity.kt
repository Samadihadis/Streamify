package com.samadihadis.streamify.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "video_table")
class VideoEntity(
    @PrimaryKey @ColumnInfo(name = "v_id") val vID : Int,
    @ColumnInfo(name = "user_name") val userName : String,
    @ColumnInfo(name = "user_id") val userId : String,
    @ColumnInfo(name = "width") val width : Int,
    @ColumnInfo(name = "height") val height : Int,
    @ColumnInfo(name = "duration") val duration : Int,
    @ColumnInfo(name = "quality") val quality : String,
    @ColumnInfo(name = "image_url") val imageUrl : String,
    @ColumnInfo(name = "size") val size : String,
    @ColumnInfo(name = "url") val url : String,
    @ColumnInfo(name = "local_path") val localPath : String,
    @ColumnInfo(name = "is_favorite") var isFavorite : Boolean,
): Serializable
