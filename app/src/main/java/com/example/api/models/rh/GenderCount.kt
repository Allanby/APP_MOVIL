package com.example.api.models.rh

import com.google.gson.annotations.SerializedName

data class GenderCount(

    @SerializedName("_id")
    val gender: String, // 'M' o 'F'

    val total: Int
)
