package com.go0ose.agedeterminant.presentation.mainScreen.pages.favorite.recycler

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.go0ose.agedeterminant.presentation.model.ItemAge

class FavoriteAgeAdapter(
    private val listener: Listener
) : RecyclerView.Adapter<FavoriteAgeViewHolder>() {

    var items: List<ItemAge> = emptyList()
    private var isEnable: Boolean = false

    val itemSelectedList = mutableListOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAgeViewHolder {
        return FavoriteAgeViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: FavoriteAgeViewHolder, position: Int) {
        bindItem(position, isEnable, holder)
    }

    override fun getItemCount() = items.size

    fun submitList(data: List<ItemAge>, enable: Boolean) {

        itemSelectedList.clear()
        isEnable = enable
        items = data
        notifyDataSetChanged()
    }

    private fun bindItem(
        position: Int,
        isEnable: Boolean,
        holder: FavoriteAgeViewHolder
    ) {

        holder.name.text = items[position].name

        if (isEnable) {
            holder.checkbox.visibility = View.VISIBLE

            holder.checkbox.setOnCheckedChangeListener { _, _ ->
                if (holder.checkbox.isChecked) {
                    itemSelectedList.add(position)
                } else {
                    if (itemSelectedList.contains(position)) {
                        itemSelectedList.remove(position)
                    }
                    items[position].isChecked = false
                }
                if (itemSelectedList.isEmpty()) {
                    items.forEach {
                        it.isChecked = false
                    }
                    listener.itemSelectedIsEmpty(items)
                }
            }

            if (items[position].isChecked) {
                holder.checkbox.isChecked = true
                items[position].isChecked = false
            }

        } else {
            if (items[position].isChecked) {
                items[position].isChecked = false
            }
            holder.root.setOnClickListener {
                listener.onItemClick(items[position])
            }

            holder.checkbox.visibility = View.GONE

            holder.root.setOnLongClickListener {
                listener.onLongItemClick(items, items[position])
                true
            }
        }
    }
}
