package com.example.api.models.rh


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retrofitPrueba {
    private const val BASE_URL = "https://backendmovil-fya8c6bvgjezh0bc.westus3-01.azurewebsites.net/"

    val api: ApiClient by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiClient::class.java)
    }
}
