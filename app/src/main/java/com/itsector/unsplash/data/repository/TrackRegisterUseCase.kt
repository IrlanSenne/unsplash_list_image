package com.itsector.unsplash.data.repository

interface TrackRegisterUseCase {
    suspend fun trackRegister(photoId: String)
}