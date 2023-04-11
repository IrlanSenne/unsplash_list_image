package com.itsector.unsplash.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.itsector.unsplash.api.entities.PhotoEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class PhotosPagingSource(
    private val repository: MainRepository
) : PagingSource<Int, PhotoEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoEntity> {
        val currentPage = params.key ?: 1
        val response = repository.getPhotos(currentPage)
        return try {
            val photos = suspendCoroutine<List<PhotoEntity>> { continuation ->
                response.enqueue(object : Callback<List<PhotoEntity>> {
                    override fun onResponse(
                        call: Call<List<PhotoEntity>>,
                        response: Response<List<PhotoEntity>>
                    ) {
                        if (response.isSuccessful) {
                            continuation.resume(response.body()?.toMutableList() ?: mutableListOf())
                        } else {
                            continuation.resumeWithException(Exception("Network call failed"))
                        }
                    }

                    override fun onFailure(call: Call<List<PhotoEntity>>, t: Throwable) {
                        continuation.resumeWithException(t)
                    }
                })
            }

            LoadResult.Page(
                data = photos,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (photos.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PhotoEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
