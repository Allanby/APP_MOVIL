package com.example.api.models.rh

import com.google.gson.annotations.SerializedName

data class Employee(
    @SerializedName("_id") var id: String? = null,
    @SerializedName("detallepermisos") var detallePermisos: ArrayList<Detallepermisos> = arrayListOf(),
    @SerializedName("direccion") var direccion: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("estado") var estado: String? = null,
    @SerializedName("is_active") var isActive: Boolean? = null,
    @SerializedName("numero_cedula") var numeroCedula: String? = null,
    @SerializedName("numero_inss") var numeroInss: String? = null,
    @SerializedName("primer_apellido") var primerApellido: String? = null,
    @SerializedName("primer_nombre") var primerNombre: String? = null,
    @SerializedName("segundo_apellido") var segundoApellido: String? = null,
    @SerializedName("segundo_nombre") var segundoNombre: String? = null,
    @SerializedName("sexo") var sexo: String? = null,
    @SerializedName("telefono") var telefono: String? = null
)



data class DetallePermisos(
    @SerializedName("Activo") var activo: Boolean? = null,
    @SerializedName("descripcion") var descripcion: String? = null,
    @SerializedName("fechafin") var fechaFin: String? = null,
    @SerializedName("fechainicio") var fechaInicio: String? = null,
    @SerializedName("idpermisos") var idPermisos: String? = null
)



data class EmployeeByCategory(
    @SerializedName("_id") var categoria: String? = null,
    @SerializedName("total") var total: Int? = null
)



data class EmployeeActivos(
    @SerializedName("total_activos") var totalActivos: Int? = null
)



data class EmployeeByContract(
    @SerializedName("total_empleados") var totalEmpleados: Int? = null,
    @SerializedName("tipoContrato") var tipoContrato: String? = null
)



data class EmployeeByAgeRange(
    @SerializedName("total") var total: Int? = null,
    @SerializedName("rango") var rango: String? = null
)







