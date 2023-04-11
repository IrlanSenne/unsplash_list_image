package com.itsector.unsplash.di

import com.itsector.unsplash.MainViewModel
import com.itsector.unsplash.api.UnsplashApi
import com.itsector.unsplash.data.MainRepository
import com.itsector.unsplash.data.MainRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://api.unsplash.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UnsplashApi::class.java)
    }

    single<MainRepository> { MainRepositoryImpl(get()) }

    viewModel { MainViewModel(get()) }
}