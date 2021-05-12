package com.example.camdroid.utils

import android.app.Application
import com.example.camdroid.di.RepositoryModule
import com.example.camdroid.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class AppConfig:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin { 
            androidContext(this@AppConfig)
            modules(listOf(RepositoryModule, viewModelModule))
        }
    }
}