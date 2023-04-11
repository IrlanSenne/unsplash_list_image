package com.itsector.unsplash.data

import com.itsector.unsplash.api.UnsplashApi
import com.itsector.unsplash.api.entities.PhotosEntity
import retrofit2.Call

class MainRepositoryImpl(
    private val api: UnsplashApi
): MainRepository {
    override fun doNetworkCall() : Call<List<PhotosEntity>> {
        return api.getPhotos(CLIENT_ID, PAGE, PER_PAGE)
    }
}

const val CLIENT_ID = "52ed5e63ad1915fed2bbfd2326aade6b8549b050fc8367a7c105567476df2a81"
const val PAGE = 1
const val PER_PAGE = 10