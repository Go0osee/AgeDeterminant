package com.go0ose.agedeterminant.data.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URl = "https://api.agify.io/"

    private var gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    private fun getAgeClient() = Retrofit.Builder()
        .baseUrl(BASE_URl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun getAgeApi(): AgeApi = getAgeClient().create(AgeApi::class.java)
}