package com.itsector.unsplash.data

import com.itsector.unsplash.api.entities.PhotoEntity
import retrofit2.Call

interface MainRepository {
    fun getPhotos(currentPage: Int): Call<List<PhotoEntity>>
}