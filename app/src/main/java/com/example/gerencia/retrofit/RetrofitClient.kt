package com.example.gerencia.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GerenciaRetrofitClient {

    private const val BASE_URL = "https://backendmovil-fya8c6bvgjezh0bc.westus3-01.azurewebsites.net/"
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val gerenciaApiService: GerenciaApiService by lazy {
        retrofit.create(GerenciaApiService::class.java)
    }
}
