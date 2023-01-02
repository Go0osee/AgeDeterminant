package com.go0ose.agedeterminant.domain

import com.go0ose.agedeterminant.data.retrofit.AgeResponse
import com.go0ose.agedeterminant.data.storage.cache.entity.AgeEntity

interface AgeRepository {

    suspend fun getAge(name: String): AgeResponse

    suspend fun getAllAge(): List<AgeEntity>

    suspend fun saveAge(ageEntity: AgeEntity)

}