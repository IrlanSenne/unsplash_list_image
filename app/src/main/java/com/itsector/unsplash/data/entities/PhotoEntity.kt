package com.itsector.unsplash.data.entities

import com.google.gson.annotations.SerializedName

data class PhotoEntity(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("likes")
    val likes: Int? = 0,
    @SerializedName("urls")
    val urls: Urls? = null
) {
    data class Urls(
        val small: String = "",
    )
}