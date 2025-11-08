package com.example.api.models.rh

import com.google.gson.annotations.SerializedName

data class NominaListResponce_Compo(
    @SerializedName("mes_pagado")
    val mesPagado: String,

    @SerializedName("fecha_pago")
    val fechaPago: String,

    @SerializedName("total")
    val total: Double,

    @SerializedName("deducciones")
    val deducciones: Double
)
