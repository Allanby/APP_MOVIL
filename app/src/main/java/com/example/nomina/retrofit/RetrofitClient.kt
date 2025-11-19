package com.example.nomina.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "http://20.150.223.108:8084/api/"

    // El resto del código no necesita ningún cambio.
    // 'lazy' asegura que la instancia de Retrofit se cree solo una vez, de manera eficiente.
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // La creación del servicio de la API sigue siendo la misma.
    // Retrofit usará la nueva BASE_URL para construir las rutas completas a los endpoints.
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
