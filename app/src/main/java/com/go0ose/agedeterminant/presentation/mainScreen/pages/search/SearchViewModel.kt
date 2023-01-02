package com.go0ose.agedeterminant.presentation.mainScreen.pages.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.go0ose.agedeterminant.R
import com.go0ose.agedeterminant.domain.AgeInteractor
import com.go0ose.agedeterminant.domain.FavoriteAgeInteractor
import com.go0ose.agedeterminant.presentation.model.Age
import com.go0ose.agedeterminant.presentation.model.MainState
import com.go0ose.agedeterminant.utils.convertToLatin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val ageInteractor: AgeInteractor,
    private val favoriteAgeInteractor: FavoriteAgeInteractor
) : ViewModel() {

    private val _mainState = MutableStateFlow<MainState>(MainState.DefaultState)
    val mainState: StateFlow<MainState> = _mainState

    fun searchAge(query: String) {
        val name = query.convertToLatin()

        viewModelScope.launch {
            _mainState.value = MainState.LoadingState
            if (validate(name)) {

                val ageFromDataBase = ageInteractor.getAllAge().find { it.name == name }

                if (ageFromDataBase != null) {
                    _mainState.value = MainState.SuccessState(ageFromDataBase)
                } else {
                    try {
                        val age = ageInteractor.getAgeResponse(name)
                        ageInteractor.saveAge(age)

                        if (age.age != 0) {
                            _mainState.value = MainState.SuccessState(age)
                        } else {
                            _mainState.value = MainState.ErrorState(R.string.error)
                        }
                    } catch (e: Throwable) {
                        _mainState.value = MainState.ErrorState(R.string.server_error)
                    }
                }
            } else {
                _mainState.value = MainState.ErrorState(R.string.validation_error)
            }
        }
    }

    private fun validate(input: String): Boolean {
        val pattern = "^[a-zA-Zа-яА-Я]{1,15}$"
        val regex = Regex(pattern)
        return regex.matches(input)
    }

    fun setStateDefault() {
        _mainState.value = MainState.DefaultState
    }

    fun addFavoriteAge(age: Age) {
        viewModelScope.launch {
            if(favoriteAgeInteractor.getAllFavoriteAge().contains(age)){
                _mainState.value = MainState.AddFavoriteState(R.string.already_added)
            } else {
                favoriteAgeInteractor.addFavoriteAge(age)
                _mainState.value = MainState.AddFavoriteState(R.string.added)
            }
        }
    }
}