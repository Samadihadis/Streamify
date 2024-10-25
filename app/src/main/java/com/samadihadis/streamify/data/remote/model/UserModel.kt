package com.samadihadis.streamify.data.remote.model

import java.io.Serializable

data class UserModel(
    val id: Int,
    val name: String,
    val url: String
): Serializable {
    fun getUserName() = url.replace("https://www.pexels.com/", "")
}
