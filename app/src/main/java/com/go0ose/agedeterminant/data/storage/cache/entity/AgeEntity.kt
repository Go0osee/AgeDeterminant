package com.go0ose.agedeterminant.data.storage.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.String

@Entity(tableName = "age")
data class AgeEntity(
    @PrimaryKey
    var name: String,
    @ColumnInfo(name = "age")
    var age: Int
)