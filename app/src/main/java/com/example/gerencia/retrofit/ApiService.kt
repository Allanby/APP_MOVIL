package com.example.gerencia.retrofit

import com.example.api.models.gerencia.TotalCargos
import com.example.api.models.gerencia.TotalDepartamentos
import com.example.api.models.gerencia.TotalJornadas
import com.example.gerencia.viewModel.JornadasUiState
import retrofit2.Response
import retrofit2.http.GET

interface GerenciaApiService {
    @GET("deptos/total")
    suspend fun getTotalDepartamentos(): Response <TotalDepartamentos>

    @GET("jornadas/total")
    suspend fun getTotalJornadas(): Response<TotalJornadas>

    @GET("cargos/total")
    suspend fun getTotalCargos(): Response <TotalCargos>


}



