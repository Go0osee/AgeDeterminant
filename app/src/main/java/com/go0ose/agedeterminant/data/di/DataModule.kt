package com.go0ose.agedeterminant.data.di

import com.go0ose.agedeterminant.data.AgeRepositoryImpl
import com.go0ose.agedeterminant.data.FavoriteAgeRepositoryImpl
import com.go0ose.agedeterminant.domain.AgeRepository
import com.go0ose.agedeterminant.domain.FavoriteAgeRepository
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @Binds
    fun bindAgeRepository(ageRepositoryImpl: AgeRepositoryImpl) : AgeRepository

    @Binds
    fun bindFavoriteAgeRepository(favoriteAgeRepositoryImpl: FavoriteAgeRepositoryImpl) : FavoriteAgeRepository
}