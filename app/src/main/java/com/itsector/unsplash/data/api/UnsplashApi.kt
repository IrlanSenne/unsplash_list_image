package com.itsector.unsplash.data.api

import com.itsector.unsplash.BuildConfig
import com.itsector.unsplash.data.entities.PhotoEntity
import retrofit2.Call
import retrofit2.http.*

interface UnsplashApi {
    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("photos")
    fun getPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<List<PhotoEntity>>

    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @POST("photos/{id}/download")
    fun trackPhotoDownload(
        @Path("id") photoId: String
    ): Call<Unit>
}