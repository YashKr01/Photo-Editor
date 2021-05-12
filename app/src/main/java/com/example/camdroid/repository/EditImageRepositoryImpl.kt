package com.example.camdroid.repository

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.InputStream

class EditImageRepositoryImpl(private val context: Context) : EditImageRepository {

    override suspend fun prepareImagePreview(imageUriUri: Uri): Bitmap? {
        getInputStreamFromUri(imageUriUri)?.let { inputStream ->
            val originalBitMap = BitmapFactory.decodeStream(inputStream)
            val width = context.resources.displayMetrics.widthPixels
            val height = ((originalBitMap.height * width) / originalBitMap.width)
            return Bitmap.createScaledBitmap(originalBitMap, width, height, false)
        } ?: return null
    }

    private fun getInputStreamFromUri(uri: Uri): InputStream? {
        return context.contentResolver.openInputStream(uri)
    }

}