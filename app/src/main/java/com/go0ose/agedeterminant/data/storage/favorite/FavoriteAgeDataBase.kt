package com.go0ose.agedeterminant.data.storage.favorite

import androidx.room.Database
import androidx.room.RoomDatabase
import com.go0ose.agedeterminant.data.storage.favorite.entity.FavoriteAgeEntity

@Database(
    entities = [FavoriteAgeEntity::class],
    version = FavoriteAgeDataBase.VERSION
)
abstract class FavoriteAgeDataBase: RoomDatabase()  {
    companion object {
        const val VERSION = 1
    }
    abstract fun getFavoriteAgeDao(): FavoriteAgeDao
}