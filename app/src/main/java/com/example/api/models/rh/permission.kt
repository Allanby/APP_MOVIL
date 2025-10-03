package com.example.api.models.rh
import com.google.gson.annotations.SerializedName

data class permission(
    @SerializedName("_id"              ) var Id              : String?                    = null,
    @SerializedName("detallepermisos"  ) var detallepermisos : ArrayList<Detallepermisos> = arrayListOf(),
    @SerializedName("direccion"        ) var direccion       : String?                    = null,
    @SerializedName("email"            ) var email           : String?                    = null,
    @SerializedName("estado"           ) var estado          : String?                    = null,
    @SerializedName("is_active"        ) var isActive        : Boolean?                   = null,
    @SerializedName("numero_cedula"    ) var numeroCedula    : String?                    = null,
    @SerializedName("numero_inss"      ) var numeroInss      : String?                    = null,
    @SerializedName("primer_apellido"  ) var primerApellido  : String?                    = null,
    @SerializedName("primer_nombre"    ) var primerNombre    : String?                    = null,
    @SerializedName("segundo_apellido" ) var segundoApellido : String?                    = null,
    @SerializedName("segundo_nombre"   ) var segundoNombre   : String?                    = null,
    @SerializedName("sexo"             ) var sexo            : String?                    = null,
    @SerializedName("telefono"         ) var telefono        : String?                    = null

    )
