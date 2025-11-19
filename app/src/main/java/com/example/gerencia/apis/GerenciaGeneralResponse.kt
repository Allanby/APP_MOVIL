package com.example.api.models.gerencia

import com.google.gson.annotations.SerializedName

data class TotalDepartamentos(
    @SerializedName("total_departamentos")
    val total: Int
)


data class TotalJornadas(
    @SerializedName("total_jornadas" ) var totalJornadas : Int? = null

)

data class TotalCargos(
    @SerializedName("total_cargos" ) var totalCargos : Int? = null

)
