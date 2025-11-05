package com.example.api

import DatosNomina
import com.example.api.models.rh.Employee
import com.example.api.models.rh.EmployeeActivos
import com.example.api.models.rh.EmployeeByCategory
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// Esta interfaz contendrá TODAS las llamadas a la API de tu aplicación.
interface ApiService {

    // --- Endpoints de RRHH (ejemplos que ya podrías tener) ---
    @GET("empleados")
    suspend fun getAllEmployees(): Response<List<Employee>>

    @GET("empleados/activos")
    suspend fun getActiveEmployees(): Response<EmployeeActivos>

    @GET("empleados/porcategoria")
    suspend fun getEmployeesByCategory(): Response<List<EmployeeByCategory>>


    // --- NUEVOS Endpoints para NÓMINA ---
    // Agrega aquí las nuevas funciones para el módulo de nómina.

    /**
     * Obtiene los datos consolidados de la nómina para un mes y año específicos.
     * Ejemplo de llamada: /api/nomina/datos?anio=2025&mes=10
     */
    @GET("nomina/datos")
    suspend fun getDatosNomina(
        @Query("anio") anio: Int,
        @Query("mes") mes: Int
    ): Response<DatosNomina> // Usará el data class que definiremos a continuación.

    // Puedes añadir más endpoints de nómina aquí en el futuro...
    // @GET("nomina/historial/{empleadoId}")
    // suspend fun getHistorialNominaEmpleado(...)
}
