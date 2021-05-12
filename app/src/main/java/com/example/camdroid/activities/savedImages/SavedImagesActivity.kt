package com.example.camdroid.activities.savedImages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.camdroid.databinding.ActivitySavedImagesBinding
import com.example.camdroid.utils.displayToast
import com.example.camdroid.viewmodels.SavedImagesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SavedImagesActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySavedImagesBinding
    private val viewModel: SavedImagesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySavedImagesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObserver()
        setListener()
        viewModel.loadSavedImages()

    }

    private fun setupObserver() {
        viewModel.savedImagesUiState.observe(this, {
            val savedImagesDataState = it ?: return@observe
            binding.savedImagesProgressBar.visibility =
                if (savedImagesDataState.isLoading) View.VISIBLE else View.GONE
            savedImagesDataState.savedImages?.let { savedImages ->
                displayToast("${savedImages.size} images loaded")
            } ?: run {
                savedImagesDataState.error?.let { error ->
                    displayToast(error)
                }
            }
        })
    }

    private fun setListener() {
        binding.imageBack.setOnClickListener {
            onBackPressed()
        }
    }

}