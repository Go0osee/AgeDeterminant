package com.go0ose.agedeterminant.data.di

import com.go0ose.agedeterminant.data.retrofit.AgeApi
import com.go0ose.agedeterminant.data.retrofit.RetrofitClient
import dagger.Module
import dagger.Provides

@Module
class RetrofitModule {

    @Provides
    fun provideAgeApi(): AgeApi {
        return RetrofitClient.getAgeApi()
    }
}
