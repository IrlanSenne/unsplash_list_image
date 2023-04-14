package com.itsector.unsplash.data.repository

import com.itsector.unsplash.data.entities.PhotoEntity
import retrofit2.Call

interface MainRepository {
    fun getPhotos(currentPage: Int): Call<List<PhotoEntity>>
}