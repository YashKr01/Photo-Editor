package com.example.camdroid.repository

import android.graphics.Bitmap
import android.net.Uri

interface EditImageRepository {
    suspend fun prepareImagePreview(imageUriUri: Uri): Bitmap?
}