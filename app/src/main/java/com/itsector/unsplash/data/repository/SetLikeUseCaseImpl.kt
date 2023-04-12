package com.itsector.unsplash.data.repository

import com.itsector.unsplash.data.api.UnsplashApi

class SetLikeUseCaseImpl(
    private val api: UnsplashApi
): SetLikeUseCase {
    override fun setLike(photoId: String) {
        try {
            api.trackPhotoDownload(photoId)
        } catch (e: Throwable) {
            throw Throwable(ERROR_API)
        }
    }
}