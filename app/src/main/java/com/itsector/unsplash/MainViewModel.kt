package com.itsector.unsplash

import androidx.lifecycle.ViewModel
import com.itsector.unsplash.api.entities.PhotosEntity
import com.itsector.unsplash.data.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {

    private val _listPhotos = MutableStateFlow<List<PhotosEntity>?>(null)
    val listPhotos: StateFlow<List<PhotosEntity>?> = _listPhotos

    init {
        getPhotos()
    }

    private fun getPhotos() {
        repository.doNetworkCall().enqueue(object : Callback<List<PhotosEntity>> {
            override fun onResponse(
                call: Call<List<PhotosEntity>>,
                response: Response<List<PhotosEntity>>
            ) {
                if (response.isSuccessful) {
                    _listPhotos.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<PhotosEntity>>, t: Throwable) {}
        })
    }
}