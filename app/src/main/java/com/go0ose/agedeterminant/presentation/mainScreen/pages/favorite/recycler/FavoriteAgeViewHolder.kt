package com.go0ose.agedeterminant.presentation.mainScreen.pages.favorite.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.go0ose.agedeterminant.databinding.ItemNameBinding
import com.go0ose.agedeterminant.presentation.model.ItemAge

class FavoriteAgeViewHolder(
    private val binding: ItemNameBinding,
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup
        ) =
            FavoriteAgeViewHolder(
                ItemNameBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }

    val checkbox = binding.checkbox
    val name = binding.name
    val root = binding.root
}