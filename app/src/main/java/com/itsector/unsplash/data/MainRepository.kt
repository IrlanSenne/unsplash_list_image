package com.itsector.unsplash.data

import com.itsector.unsplash.api.entities.PhotosEntity
import retrofit2.Call

interface MainRepository {
    fun doNetworkCall(): Call<List<PhotosEntity>>
}