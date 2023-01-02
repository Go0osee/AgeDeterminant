package com.go0ose.agedeterminant.data

import com.go0ose.agedeterminant.data.retrofit.AgeApi
import com.go0ose.agedeterminant.data.retrofit.AgeResponse
import com.go0ose.agedeterminant.data.storage.cache.AgeDao
import com.go0ose.agedeterminant.data.storage.cache.entity.AgeEntity
import com.go0ose.agedeterminant.domain.AgeRepository

import javax.inject.Inject

class AgeRepositoryImpl @Inject constructor(
    private val ageApi: AgeApi,
    private val ageDao: AgeDao
) : AgeRepository {

    override suspend fun getAge(name: String): AgeResponse {
        return ageApi.getAgeResponse(name = name)
    }

    override suspend fun getAllAge(): List<AgeEntity> {
        return ageDao.getAllAge()
    }

    override suspend fun saveAge(ageEntity: AgeEntity) {
        ageDao.saveAge(ageEntity)
    }
}