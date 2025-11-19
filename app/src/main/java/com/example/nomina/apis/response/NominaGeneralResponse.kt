package com.example.api.models.nomina

import com.google.gson.annotations.SerializedName

data class DatosNomina(
    val mes: String,
    val anio: Int,
    val proyeccion: Double,
    val deducciones: Double,
    val beneficios: Double,
    val distribucion: List<DistribucionDepartamental>
)

data class NominaMensual(
    @SerializedName("Anio")
    val anio: Int,

    @SerializedName("Mes")
    val mes: String,

    @SerializedName("SalarioBruto")
    val salarioBruto: Double
)

data class TotalBeneficio(
    @SerializedName("TotalBeneficio")
    val totalBeneficio: String
)

data class TotalDeduccion(
    @SerializedName("TotalDeduccion")
    val totalDeduccion: String
)

data class Proyeccion(
    @SerializedName("SalarioBruto")
    val salarioBruto: String
)

data class SalarioBase(
    @SerializedName("Cargo")
    val cargo: String,

    @SerializedName("SexoF")
    val salarioFemenino: Double,

    @SerializedName("SexoM")
    val salarioMasculino: Double
)

data class DistribucionDepartamental(
    @SerializedName("Departamento")    val departamento: String,

    @SerializedName("SalarioNeto")
    val salarioNeto: Double,

    @SerializedName("DistribucionPorcentual")
    val distribucionPorcentual: Double,

    @SerializedName("DistribucionPorcentualTexto")
    val distribucionPorcentualTexto: String
)






