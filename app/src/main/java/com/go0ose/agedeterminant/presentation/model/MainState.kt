package com.go0ose.agedeterminant.presentation.model

sealed class MainState {

    object LoadingState : MainState()
    object DefaultState : MainState()
    class AddFavoriteState(val message: Int) : MainState()
    class SuccessState(val age: Age): MainState()
    class ErrorState(val message: Int): MainState()
}

