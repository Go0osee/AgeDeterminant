package com.go0ose.agedeterminant.domain

import com.go0ose.agedeterminant.data.storage.favorite.entity.FavoriteAgeEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteAgeRepository {

    suspend fun getFavoriteAge(): List<FavoriteAgeEntity>

    suspend fun saveFavoriteAge(favoriteAgeEntity: FavoriteAgeEntity)

    suspend fun deleteFavoriteAge(listFavoriteAgeEntity: List<FavoriteAgeEntity>)

    fun getFLowFavoriteAge(): Flow<List<FavoriteAgeEntity>>
}