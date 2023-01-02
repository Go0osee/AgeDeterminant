package com.go0ose.agedeterminant.presentation.model

sealed class FavoriteState {

    class DeleteState(
        val list: List<ItemAge>,
        val show: Boolean = true
    ) : FavoriteState()

    class DefaultState(
        val list: List<ItemAge>,
        val show: Boolean = false
    ) : FavoriteState()
}
