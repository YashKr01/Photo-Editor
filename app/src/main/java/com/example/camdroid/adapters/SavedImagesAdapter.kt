package com.example.camdroid.adapters

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.camdroid.databinding.ItemContainerSavedImageBinding
import com.example.camdroid.listeners.SavedImagesListener
import java.io.File

class SavedImagesAdapter(
    private val savedImages: List<Pair<File, Bitmap>>,
    private val savedImagesListener: SavedImagesListener
) :
    RecyclerView.Adapter<SavedImagesAdapter.SavedImagesViewHolder>() {

    inner class SavedImagesViewHolder(val binding: ItemContainerSavedImageBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedImagesViewHolder {
        val binding = ItemContainerSavedImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SavedImagesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SavedImagesViewHolder, position: Int) {
        with(holder) {
            with(savedImages[position]) {
                binding.imageSaved.setImageBitmap(second)
                binding.imageSaved.setOnClickListener {
                    savedImagesListener.onImageClicked(first)
                }
            }
        }
    }

    override fun getItemCount() = savedImages.size

}