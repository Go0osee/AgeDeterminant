package com.go0ose.agedeterminant.presentation.mainScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.go0ose.agedeterminant.presentation.model.ItemAge

class SharedViewModel : ViewModel() {

    private val _itemAge = MutableLiveData<ItemAge>()
    val itemAge: LiveData<ItemAge> get() = _itemAge

    fun setValue(itemAge: ItemAge) {
        _itemAge.value = itemAge
    }
}