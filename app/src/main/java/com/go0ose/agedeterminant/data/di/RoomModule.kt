package com.go0ose.agedeterminant.data.di

import android.content.Context
import androidx.room.Room
import com.go0ose.agedeterminant.data.storage.cache.AgeDao
import com.go0ose.agedeterminant.data.storage.cache.AgeDataBase
import com.go0ose.agedeterminant.data.storage.favorite.FavoriteAgeDao
import com.go0ose.agedeterminant.data.storage.favorite.FavoriteAgeDataBase
import dagger.Module
import dagger.Provides

@Module
class RoomModule {
    @Provides
    fun provideAgeDataBase(
        context: Context
    ): AgeDataBase {
        return Room.databaseBuilder(
            context,
            AgeDataBase::class.java,
            "age"
        ).build()
    }

    @Provides
    fun provideAgeDao(
        ageDataBase: AgeDataBase
    ): AgeDao {
        return ageDataBase.getAgeDao()
    }

    @Provides
    fun provideFavoriteAgeDataBase(
        context: Context
    ): FavoriteAgeDataBase {
        return Room.databaseBuilder(
            context,
            FavoriteAgeDataBase::class.java,
            "favorite"
        ).build()
    }

    @Provides
    fun provideFavoriteAgeDao(
        favoriteAgeDataBase: FavoriteAgeDataBase
    ): FavoriteAgeDao {
        return favoriteAgeDataBase.getFavoriteAgeDao()
    }
}