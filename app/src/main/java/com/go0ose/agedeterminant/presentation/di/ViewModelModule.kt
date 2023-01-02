package com.go0ose.agedeterminant.presentation.di

import androidx.lifecycle.ViewModel
import com.go0ose.agedeterminant.presentation.mainScreen.pages.favorite.FavoriteViewModel
import com.go0ose.agedeterminant.presentation.mainScreen.pages.search.SearchViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    fun bindFavoriteViewModel(favoriteViewModel: FavoriteViewModel): ViewModel
}