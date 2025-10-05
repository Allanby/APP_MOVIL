package com.example.api.models.rh

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private const val BASE_URL = "https://backend-movil-v45i.onrender.com/"
    //private const val BASE_URL = "http://192.168.176.131:5000/"
    // 🔹 Cliente HTTP con logging y timeouts
    fun create(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

