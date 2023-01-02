package com.go0ose.agedeterminant.domain

import com.go0ose.agedeterminant.presentation.model.Age
import com.go0ose.agedeterminant.utils.toAge
import com.go0ose.agedeterminant.utils.toFavoriteAgeEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteAgeInteractorImpl @Inject constructor(
    private val favoriteAgeRepository: FavoriteAgeRepository
) : FavoriteAgeInteractor {
    override suspend fun addFavoriteAge(age: Age) {
        favoriteAgeRepository.saveFavoriteAge(age.toFavoriteAgeEntity())
    }

    override suspend fun getAllFavoriteAge(): List<Age> {
        return favoriteAgeRepository.getFavoriteAge().map { favoriteAgeEntity ->
            favoriteAgeEntity.toAge()
        }
    }

    override suspend fun deleteFavoriteAge(listAge: List<Age>) {
        favoriteAgeRepository.deleteFavoriteAge(listAge.map { age ->
            age.toFavoriteAgeEntity()
        })
    }

    override fun getFlowAllFavoriteAge(): Flow<List<Age>> {
        return favoriteAgeRepository.getFLowFavoriteAge().map { list ->
            list.map { favoriteAgeEntity ->
                favoriteAgeEntity.toAge()
            }
        }
    }
}