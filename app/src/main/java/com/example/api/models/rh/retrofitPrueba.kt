package com.example.api.models.rh


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retrofitPrueba {
    private const val BASE_URL = "https://backend-movil-v45i.onrender.com/"

    val api: ApiClient by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiClient::class.java)
    }
}
