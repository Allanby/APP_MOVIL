package com.example.api.models.rh

import com.google.gson.annotations.SerializedName

data class EmployeeTotalResponse(
    @SerializedName("total_activos") val total: Int
)

data class EmployeeDepartamentResponse(
    @SerializedName("_id") val department: String,
    @SerializedName("total_empleados") val total: Int



)
