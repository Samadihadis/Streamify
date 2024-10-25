package com.samadihadis.streamify.data

import java.io.Serializable

class MainVideoModel(
    val vID : Int,
    val userName : String,
    val userId : String,
    val width : Int,
    val height : Int,
    val duration : Int,
    val quality : String,
    val imageUrl : String,
    val size : String,
    val url : String,
    val localPath : String,
    var isFavorite : Boolean,
): Serializable
