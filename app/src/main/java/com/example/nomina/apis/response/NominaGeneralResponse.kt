package com.example.api.models.nomina

import com.google.gson.annotations.SerializedName

/**
 * Representa los datos generales de la nómina, incluyendo la proyección,
 * deducciones, beneficios y su distribución departamental.
 */
data class DatosNomina(
    val mes: String,
    val anio: Int,
    val proyeccion: Double,
    val deducciones: Double,
    val beneficios: Double,
    val distribucion: List<DistribucionDepartamental>
)

/**
 * Representa la distribución de la nómina por departamento.
 */
//data class DistribucionDepartamental(
//    val departamento: String,
//    val monto: Double
//)

/**
 * Representa los datos del salario bruto para un mes y año específicos.
 * Se utilizan anotaciones @SerializedName para mapear los nombres del JSON.
 */
data class NominaMensual(
    @SerializedName("Anio")
    val anio: Int,

    @SerializedName("Mes")
    val mes: String, // Puede venir como número o texto en el JSON ("1", "Enero", etc.)

    @SerializedName("SalarioBruto")
    val salarioBruto: Double
)

data class TotalBeneficio(
    @SerializedName("TotalBeneficio")
    val totalBeneficio: Double
)

data class TotalDeduccion(
    @SerializedName("TotalDeduccion")
    val totalDeduccion: Double
)

data class Proyeccion(
    @SerializedName("SalarioBruto")
    val salarioBruto: Double
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

    // Usaremos este campo para mostrar directamente en la UI
    @SerializedName("DistribucionPorcentualTexto")
    val distribucionPorcentualTexto: String
)






