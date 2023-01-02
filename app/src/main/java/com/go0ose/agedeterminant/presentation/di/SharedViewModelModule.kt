package com.go0ose.agedeterminant.presentation.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.go0ose.agedeterminant.presentation.mainScreen.SharedViewModel
import dagger.Module
import dagger.Provides

@Module
class SharedViewModelModule {

    @Provides
    fun provideSharedViewModel(
        viewModelStoreOwner: ViewModelStoreOwner
    ): SharedViewModel {
        return ViewModelProvider(viewModelStoreOwner)[SharedViewModel::class.java]
    }
}