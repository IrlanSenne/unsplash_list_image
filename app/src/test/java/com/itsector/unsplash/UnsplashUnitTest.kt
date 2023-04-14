package com.itsector.unsplash

import com.itsector.unsplash.data.api.UnsplashApi
import com.itsector.unsplash.data.entities.PhotoEntity
import com.itsector.unsplash.data.repository.MainRepository
import com.itsector.unsplash.data.repository.MainRepositoryImpl
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import retrofit2.Call
import retrofit2.Response

class UnsplashUnitTest {
    private lateinit var repository: MainRepository
    private lateinit var api: UnsplashApi

    @Before
    fun setup() {
        api = mock<UnsplashApi>() { UnsplashApi::class.java }
        repository = MainRepositoryImpl(api)
    }

    @Test
    fun `getPhotos returns successful response`() = runBlocking{
        val photoEntityList = listOf(PhotoEntity())
        val call = mock<Call<List<PhotoEntity>>>()
        Mockito.`when`(call.execute()).thenReturn(Response.success(photoEntityList))
        Mockito.`when`(api.getPhotos(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt())).thenReturn(call)

        val result = runBlocking { api.getPhotos(1, 10) }
        assertNotNull(result.execute())
    }


    @Test
    fun `get photos - api error`() = runBlocking {
        val errorResponse = Response.error<List<PhotoEntity>>(
            404,
            ResponseBody.create(MediaType.parse("application/json"), "Not Found")
        )
        val call = mock<Call<List<PhotoEntity>>>()
        Mockito.`when`(call.execute()).thenReturn(errorResponse)
        Mockito.`when`(api.getPhotos(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt())).thenReturn(call)

        val result = api.getPhotos(1, 10)
        assertTrue(result.execute().isSuccessful.not())
    }
}
