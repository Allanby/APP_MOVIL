package com.example.api.models.rh

import com.google.gson.annotations.SerializedName

data class Detallepermisos(

    @SerializedName("Activo"      ) var Activo      : Boolean? = null,
    @SerializedName("descripcion" ) var descripcion : String?  = null,
    @SerializedName("fechafin"    ) var fechafin    : String?  = null,
    @SerializedName("fechainicio" ) var fechainicio : String?  = null,
    @SerializedName("idpermisos"  ) var idpermisos  : String?  = null

)
