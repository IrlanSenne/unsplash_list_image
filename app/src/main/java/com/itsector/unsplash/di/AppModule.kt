package com.itsector.unsplash.di

import com.itsector.unsplash.ui.MainViewModel
import com.itsector.unsplash.data.api.UnsplashApi
import com.itsector.unsplash.data.repository.MainRepository
import com.itsector.unsplash.data.repository.MainRepositoryImpl
import com.itsector.unsplash.data.repository.TrackRegisterUseCase
import com.itsector.unsplash.data.repository.TrackRegisterCaseImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UnsplashApi::class.java)
    }

    single<MainRepository> { MainRepositoryImpl(get()) }
    single<TrackRegisterUseCase> { TrackRegisterCaseImpl(get()) }

    viewModel { MainViewModel(get(), get()) }
}

const val BASE_URL = "https://api.unsplash.com/"