package com.itsector.unsplash.data.entities

import com.google.gson.annotations.SerializedName

data class PhotoEntity(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("likes")
    val likes: Int? = 0,
    @SerializedName("liked_by_user")
    val likedByUser: Boolean? = false,
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("urls")
    val urls: Urls? = null
) {
    data class Urls(
        val small: String = "",
    )
}