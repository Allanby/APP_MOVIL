package com.example.api.models.rh

import retrofit2.http.GET


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
    suspend fun getAgeDistribution(): List<AgeRangeCount>



    //APIS DE PERMISO
    //
    @GET("permisos/mes")
    suspend fun getPermisosMes(): PermisoMesResponse

    @GET("permisos/promedio")
    suspend fun getPromedioPermisos(): PromedioPermisosResponse


    @GET("permisos/diasperdidos")
    suspend fun getDiasPerdidos(): DiasPerdidosResponse

    @GET("permisos/genero")
    suspend fun getPermisosGenero(): PermisosGeneroResponse

    @GET("permisos/departamento-popular")
    suspend fun getDepartamentoPermisos(): PermisoDepartamentoResponse


    //APIS DE CONTRATPS
    @GET("contratos/promedio-meses")
    suspend fun getPromedioMeses(): PromedioContratos

    @GET("contratos/vigentes")
    suspend fun getContratosVigentes(): ContratosVigentesResponse

    @GET("contratos/por-genero")
    suspend fun getContratosPorGenero(): List<ContratoGeneroResponse>

}

