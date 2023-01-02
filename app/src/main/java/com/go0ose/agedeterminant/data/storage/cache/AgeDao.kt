package com.go0ose.agedeterminant.data.storage.cache

import androidx.room.*
import com.go0ose.agedeterminant.data.storage.cache.entity.AgeEntity

@Dao
interface AgeDao {
    @Query("SELECT * FROM age")
    suspend fun getAllAge(): List<AgeEntity>

    @Insert
    suspend fun saveAge(ageEntity: AgeEntity)
}
