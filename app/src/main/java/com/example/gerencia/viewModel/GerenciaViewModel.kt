package com.example.gerencia.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.models.gerencia.TotalJornadas
import com.example.gerencia.retrofit.GerenciaRetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

// ---------------------------------------------------------
// UI STATES
// ---------------------------------------------------------
data class DepartamentosUiState(
    val cantidad: Int = 0,
    val isLoading: Boolean = false,
    val error: String? = null
)

data class JornadasUiState(
    val cantidad: Int = 0,
    val isLoading: Boolean = false,
    val error: String? = null
)

data class CargosUiState(
    val cantidad: Int = 0,
    val isLoading: Boolean = false,
    val error: String? = null
)

// ---------------------------------------------------------
// VIEWMODEL
// ---------------------------------------------------------
class GerenciaViewModel : ViewModel() {

    // Estados
    private val _departamentosUiState = MutableStateFlow(DepartamentosUiState())
    val departamentosUiState: StateFlow<DepartamentosUiState> = _departamentosUiState.asStateFlow()

    private val _jornadasUiState = MutableStateFlow(JornadasUiState())
    val jornadasUiState: StateFlow<JornadasUiState> = _jornadasUiState.asStateFlow()

    private val _cargosUiState = MutableStateFlow(CargosUiState())
    val cargosUiState: StateFlow<CargosUiState> = _cargosUiState.asStateFlow()

    init {
        fetchTotalDepartamentos()
        fetchTotalJornadas()
        fetchTotalCargos()
    }

    // -----------------------------------------------------
    // CARGOS
    // -----------------------------------------------------
    private fun fetchTotalCargos() {
        viewModelScope.launch {
            _cargosUiState.value = CargosUiState(isLoading = true)

            try {
                val response = GerenciaRetrofitClient.gerenciaApiService.getTotalCargos()

                if (response.isSuccessful) {
                    Log.d("GerenciaViewModel", "Response body: ${response.body()}")
                    // raw response
                    Log.d("GerenciaViewModel", "Raw response: ${response.raw()}")
                    val totalCargos = response.body()
                    if (totalCargos != null) {
                        _cargosUiState.value = CargosUiState(cantidad = totalCargos.totalCargos?:0)
                    } else {
                        _cargosUiState.value = CargosUiState(error = "Error al cargar Cargos")
                        Log.e("GerenciaViewModel", "Respuesta nula al cargar Cargos")
                    }
                } else {
                    _cargosUiState.value = CargosUiState(error = "Error al cargar Cargos")
                    Log.e("GerenciaViewModel", "Error código HTTP: ${response.code()}")
                }

            } catch (e: Exception) {
                _cargosUiState.value = CargosUiState(error = "Error al cargar Cargos")
                Log.e("GerenciaViewModel", "Excepción al cargar Cargos", e)
            }
        }
    }

    // -----------------------------------------------------
    // JORNADAS
    // -----------------------------------------------------
    private fun fetchTotalJornadas() {
        viewModelScope.launch {

            _jornadasUiState.value = _jornadasUiState.value.copy(
                isLoading = true,
                error = null
            )

            try {
                val response = GerenciaRetrofitClient.gerenciaApiService.getTotalJornadas()

                if (response.isSuccessful) {
                    Log.d("fetchTotalJornadas", "Response body: ${response.body()}")
                    // raw response
                    Log.d("fetchTotalJornadas", "Raw response: ${response.raw()}")
                    val totalJornadas = response.body()
                    if (totalJornadas != null) {
                        _jornadasUiState.value = _jornadasUiState.value.copy(
                            cantidad = totalJornadas.totalJornadas ?: 0,
                            isLoading = false
                        )
                    } else {
                        _jornadasUiState.value = JornadasUiState(error = "Error al cargar Jornadas")
                        Log.e("GerenciaViewModel", "Respuesta nula en Jornadas")
                    }
                } else {
                    _jornadasUiState.value = JornadasUiState(error = "Error al cargar Jornadas")
                    Log.e("GerenciaViewModel", "Error HTTP Jornadas: ${response.code()}")
                }

            } catch (e: Exception) {
                _jornadasUiState.value = JornadasUiState(error = "Error al cargar Jornadas")
                Log.e("GerenciaViewModel", "Excepción Jornadas", e)
            }
        }
    }

    // -----------------------------------------------------
    // DEPARTAMENTOS
    // -----------------------------------------------------
    fun fetchTotalDepartamentos() {
        viewModelScope.launch {

            _departamentosUiState.value = _departamentosUiState.value.copy(
                isLoading = true,
                error = null
            )

            try {
                val response = GerenciaRetrofitClient.gerenciaApiService.getTotalDepartamentos()

                if (response.isSuccessful) {
                    Log.d("fetchTotalDepartamentos", "Response body: ${response.body()}")
                    // raw response
                    Log.d("fetchTotalDepartamentos", "Raw response: ${response.raw()}")
                    val totalDepartamentos = response.body()

                    if (totalDepartamentos != null) {
                        _departamentosUiState.value = _departamentosUiState.value.copy(
                            cantidad = totalDepartamentos.total,
                            isLoading = false
                        )
                    } else {
                        _departamentosUiState.value = _departamentosUiState.value.copy(
                            error = "Respuesta nula del servidor.",
                            isLoading = false
                        )
                    }

                } else {
                    Log.e("fetchTotalDepartamentos", "Error HTTP: ${response.code()}")
                    _departamentosUiState.value = _departamentosUiState.value.copy(
                        error = "Error HTTP: ${response.code()}",
                        isLoading = false
                    )
                }

            } catch (e: IOException) {
                _departamentosUiState.value = _departamentosUiState.value.copy(
                    error = "Error de red. Verifique su conexión.",
                    isLoading = false
                )

                Log.e("fetchTotalDepartamentos", "Error de red", e)
            }
        }
    }
}
