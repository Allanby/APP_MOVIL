package com.example.gerencia.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Objeto Singleton para gestionar la instancia de Retrofit y el servicio de la API
 * para el Módulo de Gerencia.
 */
object GerenciaRetrofitClient {

    // 1. URL base de tu API de gerencia.
    private const val BASE_URL = "https://backendmovil-fya8c6bvgjezh0bc.westus3-01.azurewebsites.net/"

    // 2. Creación de la instancia de Retrofit usando 'lazy' para eficiencia.
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            // Usamos GsonConverterFactory, como en tu otro archivo.
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // 3. Creación del servicio de la API para gerencia.
    val gerenciaApiService: GerenciaApiService by lazy {
        retrofit.create(GerenciaApiService::class.java)
    }
}
