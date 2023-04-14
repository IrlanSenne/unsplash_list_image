package com.itsector.unsplash.data.repository

import com.itsector.unsplash.data.api.UnsplashApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class TrackRegisterCaseImpl(
    private val api: UnsplashApi
): TrackRegisterUseCase {
    override suspend fun trackRegister(photoId: String) {
        val response = api.trackPhotoDownload(photoId)

        try {
            suspendCoroutine<Unit> { continuation ->
                response.enqueue(object : Callback<Unit> {
                    override fun onResponse(
                        call: Call<Unit>,
                        response: Response<Unit>
                    ) {
                        if (response.isSuccessful) {
                            continuation.resume(Unit)
                        } else {
                            continuation.resumeWithException(Exception(ERROR_API))
                        }
                    }

                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        continuation.resumeWithException(t)
                    }
                })
            }
        } catch (e: Exception) {
            print(e.message)
        }
    }
}
