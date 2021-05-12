package com.example.camdroid.repository

import android.graphics.Bitmap
import android.net.Uri
import com.example.camdroid.data.ImageFilter

interface EditImageRepository {
    suspend fun prepareImagePreview(imageUriUri: Uri): Bitmap?
    suspend fun getImageFilters(image: Bitmap): List<ImageFilter>
}