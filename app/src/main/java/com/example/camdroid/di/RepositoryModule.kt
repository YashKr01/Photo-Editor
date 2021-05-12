package com.example.camdroid.di

import com.example.camdroid.repository.EditImageRepository
import com.example.camdroid.repository.EditImageRepositoryImpl
import com.example.camdroid.repository.SavedImagesRepository
import com.example.camdroid.repository.SavedImagesRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val RepositoryModule = module {
    factory<EditImageRepository> { EditImageRepositoryImpl(androidContext()) }
    factory<SavedImagesRepository> { SavedImagesRepositoryImpl(androidContext()) }
}