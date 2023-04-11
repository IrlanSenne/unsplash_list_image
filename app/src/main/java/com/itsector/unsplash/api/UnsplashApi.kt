package com.itsector.unsplash.api

import com.itsector.unsplash.api.entities.PhotosEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {
    @GET("photos")
    fun getPhotos(
        @Query("client_id") clientId: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<List<PhotosEntity>>
}