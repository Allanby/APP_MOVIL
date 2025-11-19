package com.example.nomina.retrofit

import com.example.api.models.gerencia.TotalCargos
import com.example.api.models.gerencia.TotalDepartamentos
import com.example.api.models.nomina.DatosNomina
import com.example.api.models.nomina.DistribucionDepartamental
import com.example.api.models.nomina.NominaMensual
import com.example.api.models.nomina.Proyeccion
import com.example.api.models.nomina.SalarioBase
import com.example.api.models.nomina.TotalBeneficio
import com.example.api.models.nomina.TotalDeduccion
import com.example.api.models.rh.Employee
import com.example.api.models.rh.EmployeeActivos
import com.example.api.models.rh.EmployeeByCategory
import com.example.gerencia.viewModel.JornadasUiState
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("empleados")
    suspend fun getAllEmployees(): Response<List<Employee>>

    @GET("empleados/activos")
    suspend fun getActiveEmployees(): Response<EmployeeActivos>

    @GET("empleados/porcategoria")
    suspend fun getEmployeesByCategory(): Response<List<EmployeeByCategory>>


    // --- Endpoints para NÓMINA ---
    @GET("nomina/datos")
    suspend fun getDatosNomina(
        @Query("anio") anio: Int,
        @Query("mes") mes: Int
    ): Response<DatosNomina>


    @GET("apicubo/nomina-mensual")
    suspend fun getNominaMensual(): Response<List<NominaMensual>>

    @GET("apicubo/beneficio-total")
    suspend fun getTotalBeneficio(): Response<List<TotalBeneficio>>


    @GET("apicubo/total-deduccion")
    suspend fun getTotalDeduccion(): Response<List<TotalDeduccion>>

    @GET("apicubo/proyeccion")
    suspend fun getProyeccion(): Response<List<Proyeccion>>

    @GET("apicubo/salario-base")
    suspend fun getSalarioBase(): Response<List<SalarioBase>>

    @GET("apicubo/distribucion-departamental")
    suspend fun getDistribucionDepartamental(): Response<List<DistribucionDepartamental>>

    @GET("deptos/total")
    suspend fun getTotalDepartamentos(): TotalDepartamentos

    @GET("jornadas/total")
    suspend fun getTotalJornadas(): JornadasUiState

    @GET("cargos/total")
    suspend fun getTotalCargos(): Response<TotalCargos>
}


