package com.example.api // O donde prefieras ubicarlo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    // URL base de tu API. Asegúrate de que termine con una barra "/"
    private const val BASE_URL = "https://tu-api-base.com/api/" // <- ¡CAMBIA ESTO!

    // Creación de la instancia de Retrofit usando 'lazy'.
    // 'lazy' asegura que la instancia se cree solo una vez, la primera vez que se accede a ella.
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Propiedad pública para acceder al servicio de API.
    // Esta es la única propiedad que tus ViewModels necesitarán para hacer llamadas.
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
