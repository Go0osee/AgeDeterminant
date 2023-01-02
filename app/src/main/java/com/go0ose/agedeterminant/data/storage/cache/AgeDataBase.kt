package com.go0ose.agedeterminant.data.storage.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.go0ose.agedeterminant.data.storage.cache.entity.AgeEntity

@Database(
    entities = [AgeEntity::class],
    version = AgeDataBase.VERSION
)
abstract class AgeDataBase : RoomDatabase() {

    companion object {
        const val VERSION = 1
    }

    abstract fun getAgeDao(): AgeDao
}
