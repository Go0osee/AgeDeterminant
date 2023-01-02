package com.go0ose.agedeterminant.data.retrofit

import com.google.gson.annotations.SerializedName

data class AgeResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("age")
    val age: Int
)

