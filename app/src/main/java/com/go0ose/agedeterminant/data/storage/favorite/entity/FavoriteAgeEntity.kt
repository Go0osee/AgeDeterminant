package com.go0ose.agedeterminant.data.storage.favorite.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class FavoriteAgeEntity(
    @PrimaryKey
    var name: String,
    @ColumnInfo(name = "age")
    var age: Int
)
