package com.itsector.unsplash.api

import com.itsector.unsplash.BuildConfig
import com.itsector.unsplash.api.entities.PhotoEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashApi {
    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("photos")
    fun getPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<List<PhotoEntity>>
}