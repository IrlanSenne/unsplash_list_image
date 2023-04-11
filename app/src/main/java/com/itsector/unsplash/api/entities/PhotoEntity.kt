package com.itsector.unsplash.api.entities

data class PhotoEntity(
    val id: String? = "",
    val urls: Urls? = null
) {
    data class Urls(
        val small: String = "",
    )
}