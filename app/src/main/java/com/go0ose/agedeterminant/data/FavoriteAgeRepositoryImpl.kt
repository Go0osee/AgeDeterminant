package com.go0ose.agedeterminant.data

import com.go0ose.agedeterminant.data.storage.favorite.FavoriteAgeDao
import com.go0ose.agedeterminant.data.storage.favorite.entity.FavoriteAgeEntity
import com.go0ose.agedeterminant.domain.FavoriteAgeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteAgeRepositoryImpl @Inject constructor(
    private val favoriteAgeDao: FavoriteAgeDao
) : FavoriteAgeRepository {

    override suspend fun getFavoriteAge(): List<FavoriteAgeEntity> {
        return favoriteAgeDao.getAll()
    }

    override suspend fun saveFavoriteAge(favoriteAgeEntity: FavoriteAgeEntity) {
        favoriteAgeDao.save(favoriteAgeEntity)
    }

    override suspend fun deleteFavoriteAge(listFavoriteAgeEntity: List<FavoriteAgeEntity>) {
       favoriteAgeDao.delete(listFavoriteAgeEntity)
    }

    override fun getFLowFavoriteAge(): Flow<List<FavoriteAgeEntity>> {
        return favoriteAgeDao.getFlowAll()
    }
}