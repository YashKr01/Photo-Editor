package com.example.camdroid.di

import com.example.camdroid.repository.EditImageRepository
import com.example.camdroid.repository.EditImageRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val RepositoryModule = module {
    factory<EditImageRepository> { EditImageRepositoryImpl(androidContext()) }
}