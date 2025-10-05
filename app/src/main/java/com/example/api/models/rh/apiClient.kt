package com.example.api.models.rh

import retrofit2.http.GET
import com.example.api.models.rh.GenderCount



interface ApiClient {

    // 🔹 Lista completa de empleados
    @GET("empleados/activos")
    suspend fun getEmployees(

    ): EmployeeTotalResponse


    // 🔹 Empleados agrupados por género (hombres/mujeres)
    @GET("empleados/genero")
    suspend fun getGenderDistribution(): List<GenderCount>

    // 🔹 Empleados agrupados por departamento
    @GET("empleados/departamento")
    suspend fun getEmployeesByDepartment(): ArrayList<EmployeeDepartamentResponse>

    // 🔹 Total de empleados activos
    @GET("empleados/activos")
    suspend fun getEmployeesActivos(): EmployeeActivos

    // 🔹 Empleados agrupados por tipo de contrato
    @GET("empleados/tipo-contrato")
    suspend fun getEmployeesByContract(): List<EmployeeByContract>

    // 🔹 Empleados agrupados por rango de edad
    @GET("empleados/edad")
    suspend fun getEmployeesByAgeRange(): List<EmployeeByAgeRange>
}


// OPCIONAL, FILTRAR POR CATEGORIA esto va a depender de mis enpoinds de la api