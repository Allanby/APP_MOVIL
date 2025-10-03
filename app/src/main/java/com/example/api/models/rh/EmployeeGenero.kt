package com.example.example

import com.google.gson.annotations.SerializedName


data class ExampleJson2KtKotlin (

    @SerializedName("_id"   ) var Id    : String? = null,
    @SerializedName("total" ) var total : Int?    = null

)