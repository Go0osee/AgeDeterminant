package com.go0ose.agedeterminant.presentation.mainScreen.pages.favorite.recycler

import com.go0ose.agedeterminant.presentation.model.ItemAge

interface Listener {

    fun onLongItemClick(items: List<ItemAge>, itemAge: ItemAge)
    fun itemSelectedIsEmpty(items: List<ItemAge>)
}