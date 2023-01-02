package com.go0ose.agedeterminant.data.retrofit

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AgeApi {

    @GET(".")
    suspend fun getAgeResponse(
        @Query("name") name: String,
    ): AgeResponse
}