package com.example.camdroid.viewmodels

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.camdroid.data.ImageFilter
import com.example.camdroid.repository.EditImageRepository
import com.example.camdroid.utils.Coroutines

class EditImageViewModel(private val editImageRepository: EditImageRepository) : ViewModel() {

    //region :: Prepare Image Preview
    private val imagePreviewDataState = MutableLiveData<ImagePreviewDataState>()
    val imagePreviewUiState: LiveData<ImagePreviewDataState> get() = imagePreviewDataState

    fun prepareImagePreview(imageUri: Uri) {
        Coroutines.io {
            runCatching {
                emitImagePreviewUiState(isLoading = true)
                editImageRepository.prepareImagePreview(imageUri)
            }.onSuccess { bitmap ->
                if (bitmap != null) {
                    emitImagePreviewUiState(bitmap = bitmap)
                } else {
                    emitImagePreviewUiState(error = "Unable to prepare image preview")
                }
            }.onFailure {
                emitImagePreviewUiState(error = it.message.toString())
            }
        }
    }

    private fun emitImagePreviewUiState(
        isLoading: Boolean = false,
        bitmap: Bitmap? = null,
        error: String? = null
    ) {
        val dataState = ImagePreviewDataState(isLoading, bitmap, error)
        imagePreviewDataState.postValue(dataState)
    }

    data class ImagePreviewDataState(
        val isLoading: Boolean,
        val bitmap: Bitmap?,
        val error: String?
    )

    // endregion

    //region:: LoadImage filters

    private val imageFiltersDataState = MutableLiveData<ImageFilterDataState>()
    val imageFiltersUiState: LiveData<ImageFilterDataState> get() = imageFiltersDataState

    fun loadImageFilters(originalImage: Bitmap) {
        Coroutines.io {
            runCatching {
                emitImageFilterUiState(isLoading = true)
                editImageRepository.getImageFilters(getPreviewImage(originalImage))
            }.onSuccess { imageFilters ->
                emitImageFilterUiState(imageFilter = imageFilters)
            }.onFailure {
                emitImageFilterUiState(error = it.message.toString())
            }
        }
    }

    private fun getPreviewImage(originalImage: Bitmap): Bitmap {
        return kotlin.runCatching {
            val previewWidth = 150
            val previewHeight = originalImage.height * previewWidth / originalImage.width
            Bitmap.createScaledBitmap(originalImage, previewWidth, previewHeight, false)
        }.getOrDefault(originalImage)
    }

    private fun emitImageFilterUiState(
        isLoading: Boolean = false,
        imageFilter: List<ImageFilter>? = null,
        error: String? = null
    ) {
        val dataState = ImageFilterDataState(isLoading, imageFilter, error)
        imageFiltersDataState.postValue(dataState)
    }

    data class ImageFilterDataState(
        val isLoading: Boolean,
        val imageFilters: List<ImageFilter>?,
        val error: String?
    )

    // endregion

}