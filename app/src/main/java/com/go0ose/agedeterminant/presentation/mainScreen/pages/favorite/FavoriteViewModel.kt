package com.go0ose.agedeterminant.presentation.mainScreen.pages.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.go0ose.agedeterminant.domain.FavoriteAgeInteractor
import com.go0ose.agedeterminant.presentation.model.Age
import com.go0ose.agedeterminant.presentation.model.ItemAge
import com.go0ose.agedeterminant.presentation.model.FavoriteState
import com.go0ose.agedeterminant.presentation.model.MainState
import com.go0ose.agedeterminant.utils.toAge
import com.go0ose.agedeterminant.utils.toItemAge
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val favoriteAgeInteractor: FavoriteAgeInteractor
) : ViewModel() {

    private val _state = MutableStateFlow<FavoriteState>(FavoriteState.DefaultState(emptyList()))
    val state: StateFlow<FavoriteState> = _state

    init {
        runFlow()
    }

    fun runFlow() {
        favoriteAgeInteractor.getFlowAllFavoriteAge().map { listAge ->
            _state.value = FavoriteState.DefaultState(listAge.map { it.toItemAge() })
        }.launchIn(viewModelScope)
    }

    fun updateList(items: List<ItemAge>) {
        _state.value = FavoriteState.DefaultState(items)
    }

    fun updateDeleteList(items: List<ItemAge>, itemAge: ItemAge) {
        items.forEach {
            if (itemAge == it) {
                it.isChecked = true
            }
        }

        _state.value = FavoriteState.DeleteState(items)
    }

    fun deleteItem(itemSelectedList: MutableList<Int>, items: List<ItemAge>) {
        viewModelScope.launch {
            val deleteList = mutableListOf<Age>()
            itemSelectedList.forEach { position ->
                deleteList.add(items[position].toAge())
            }

            favoriteAgeInteractor.deleteFavoriteAge(deleteList)
        }
    }

    fun setDefaultState() {
        _state.value = FavoriteState.DefaultState((_state.value as FavoriteState.DeleteState).list)
    }
}
