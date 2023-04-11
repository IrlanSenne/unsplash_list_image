package com.itsector.unsplash.data

import com.itsector.unsplash.api.UnsplashApi
import com.itsector.unsplash.api.entities.PhotoEntity
import retrofit2.Call

class MainRepositoryImpl(
    private val api: UnsplashApi
): MainRepository {
    override fun getPhotos(currentPage: Int) : Call<List<PhotoEntity>> {
        return try {
            api.getPhotos(currentPage, PER_PAGE)
        } catch (e: Throwable) {
            throw Throwable(ERROR_API)
        }
    }
}

const val PER_PAGE = 30
const val ERROR_API = "Unknown Error"