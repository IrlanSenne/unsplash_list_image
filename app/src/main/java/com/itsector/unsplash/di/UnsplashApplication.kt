package com.itsector.unsplash.di

import android.app.Application
import com.itsector.unsplash.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class UnsplashApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@UnsplashApplication)
            modules(appModule)
        }
    }
}