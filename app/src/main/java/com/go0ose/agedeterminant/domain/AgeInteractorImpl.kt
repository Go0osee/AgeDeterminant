package com.go0ose.agedeterminant.domain

import com.go0ose.agedeterminant.presentation.model.Age
import com.go0ose.agedeterminant.utils.toAgeEntity
import com.go0ose.agedeterminant.utils.toAge
import javax.inject.Inject

class AgeInteractorImpl @Inject constructor(
    private val ageRepository: AgeRepository
) : AgeInteractor {

    override suspend fun getAgeResponse(name: String): Age {
        return ageRepository.getAge(name).toAge()
    }

    override suspend fun saveAge(ageResponse: Age) {
        ageRepository.saveAge(ageResponse.toAgeEntity())
    }

    override suspend fun getAllAge(): List<Age> {
        return ageRepository.getAllAge().map { ageEntity ->
            ageEntity.toAge()
        }
    }
}