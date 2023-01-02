package com.go0ose.agedeterminant.domain

import com.go0ose.agedeterminant.presentation.model.Age
import kotlinx.coroutines.flow.Flow

interface FavoriteAgeInteractor {

    suspend fun addFavoriteAge(age: Age)

    suspend fun getAllFavoriteAge(): List<Age>

    suspend fun deleteFavoriteAge(listAge: List<Age>)

    fun getFlowAllFavoriteAge(): Flow<List<Age>>
}