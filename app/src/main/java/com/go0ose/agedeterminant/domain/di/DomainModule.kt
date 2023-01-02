package com.go0ose.agedeterminant.domain.di

import com.go0ose.agedeterminant.domain.AgeInteractor
import com.go0ose.agedeterminant.domain.AgeInteractorImpl
import com.go0ose.agedeterminant.domain.FavoriteAgeInteractor
import com.go0ose.agedeterminant.domain.FavoriteAgeInteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindAgeInteractor(ageInteractorImpl : AgeInteractorImpl): AgeInteractor

    @Binds
    fun bindFavoriteAgeInteractor(favoriteAgeInteractorImpl : FavoriteAgeInteractorImpl): FavoriteAgeInteractor
}