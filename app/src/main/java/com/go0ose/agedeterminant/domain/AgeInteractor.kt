package com.go0ose.agedeterminant.domain

import com.go0ose.agedeterminant.presentation.model.Age


interface AgeInteractor {
    suspend fun getAgeResponse(name: String): Age

    suspend fun saveAge(ageResponse: Age)

    suspend fun getAllAge(): List<Age>
}