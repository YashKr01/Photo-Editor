package com.example.camdroid.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.camdroid.R
import com.example.camdroid.data.ImageFilter
import com.example.camdroid.databinding.ItemContainerFilterBinding
import com.example.camdroid.listeners.ImageFilterListener

class ImageFilterAdapter(
    private val imageFilters: List<ImageFilter>,
    private val imageFilterListener: ImageFilterListener
) :
    RecyclerView.Adapter<ImageFilterAdapter.ImageFilterViewHolder>() {

    private var selectedFilteredPosition = 0
    private var previousSelectedPosition = 0

    inner class ImageFilterViewHolder(val binding: ItemContainerFilterBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageFilterViewHolder {
        val binding = ItemContainerFilterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ImageFilterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageFilterViewHolder, position: Int) {
        with(holder) {
            with(imageFilters[position]) {
                binding.imageFilterPreview.setImageBitmap(filterPreview)
                binding.textFilterName.text = name
                binding.root.setOnClickListener {

                    if (position != selectedFilteredPosition) {
                        imageFilterListener.onFilterSelected(this)
                        previousSelectedPosition = selectedFilteredPosition
                        selectedFilteredPosition = position
                        with(this@ImageFilterAdapter) {
                            notifyItemChanged(previousSelectedPosition, Unit)
                            notifyItemChanged(selectedFilteredPosition, Unit)
                        }
                    }
                }
            }
            binding.textFilterName.setTextColor(
                ContextCompat.getColor(
                    binding.textFilterName.context,
                    if (selectedFilteredPosition == position) R.color.primaryDark
                    else R.color.primaryText
                )
            )
        }
    }

    override fun getItemCount() = imageFilters.size
}