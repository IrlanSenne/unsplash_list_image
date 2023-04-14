package com.itsector.unsplash.data.repository

import com.itsector.unsplash.data.api.UnsplashApi

class TrackRegisterCaseImpl(
    private val api: UnsplashApi
): TrackRegisterUseCase {
    override suspend fun trackRegister(photoId: String) {
        try {
            api.trackPhotoDownload(photoId)
        } catch (e: Throwable) {
            throw Throwable(ERROR_API)
        }
    }
}