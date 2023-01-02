package com.go0ose.agedeterminant.data.storage.favorite

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.go0ose.agedeterminant.data.storage.favorite.entity.FavoriteAgeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteAgeDao {
    @Query("SELECT * FROM favorite")
    suspend fun getAll(): List<FavoriteAgeEntity>

    @Query("SELECT * FROM favorite")
    fun getFlowAll(): Flow<List<FavoriteAgeEntity>>

    @Insert
    suspend fun save(favoriteAgeEntity: FavoriteAgeEntity)

    @Delete
    suspend fun delete(listFavoriteAgeEntity: List<FavoriteAgeEntity>)
}