// en com/example/api/models/rh/GenderCount.kt
package com.example.api.models.rh

import com.google.gson.annotations.SerializedName

// Esta clase representa CADA objeto en la lista JSON
// Ejemplo: { "_id": "M", "total": 15 }
data class GenderCount(

    // @SerializedName es crucial porque "_id" no es un nombre de variable estándar en Kotlin
    @SerializedName("_id")
    val gender: String, // 'M' o 'F'

    val total: Int
)
