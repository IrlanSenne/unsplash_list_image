package com.itsector.unsplash.api.entities

data class PhotosEntity(
    val id: String? = "",
    val description: String? = "",
    val urls: Urls? = null
) {
    data class Urls(
        val small: String = "",
    )
}