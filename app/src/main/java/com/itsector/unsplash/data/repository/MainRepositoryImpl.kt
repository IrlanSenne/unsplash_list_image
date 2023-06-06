package com.itsector.unsplash.data.repository

import com.itsector.unsplash.data.api.UnsplashApi
import com.itsector.unsplash.data.entities.PhotoEntity
import retrofit2.Call

class MainRepositoryImpl(
    private val api: UnsplashApi
): MainRepository {
    override fun getPhotos(currentPage: Int) : Call<List<PhotoEntity>> {
        return try {
            api.getPhotos(currentPage, PER_PAGE)
        } catch (e: Throwable) {
            throw Throwable(ERROR_API) // test
        }
    }
}

const val PER_PAGE = 10
const val ERROR_API = "Unknown Error"