package com.example.adminrh.nomina

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.models.nomina.DistribucionDepartamental
import com.example.api.models.nomina.NominaMensual
import com.example.api.models.nomina.SalarioBase
// ¡Asegúrate de que la ruta a tu cliente Retrofit sea la correcta!
import com.example.nomina.retrofit.RetrofitClient
import kotlinx.coroutines.launch

// El sealed class para estados de UI no se está usando, pero lo dejo por si lo necesitas a futuro.
sealed class NominaEstado {
    object Cargando : NominaEstado()
    data class Error(val mensaje: String) : NominaEstado()
}

class NominaViewModel : ViewModel() {

    // --- LiveData para la UI (No se cambian) ---
    private val _proyeccion = MutableLiveData<Double?>()
    val proyeccion: LiveData<Double?> = _proyeccion

    private val _totalDeduccion = MutableLiveData<Double?>()
    val totalDeduccion: LiveData<Double?> = _totalDeduccion

    private val _totalBeneficio = MutableLiveData<Double?>()
    val totalBeneficio: LiveData<Double?> = _totalBeneficio

    private val _nominaMensual = MutableLiveData<List<NominaMensual>>()
    val nominaMensual: LiveData<List<NominaMensual>> = _nominaMensual

    private val _distribucionDepartamental = MutableLiveData<List<DistribucionDepartamental>>()
    val distribucionDepartamental: LiveData<List<DistribucionDepartamental>> = _distribucionDepartamental

    // --- State para Compose (No se cambia) ---
    private val _salarioBase = MutableLiveData<List<SalarioBase>>()
    var salarioB by mutableStateOf(emptyList<SalarioBase>())
        private set
    val salarioBase: LiveData<List<SalarioBase>> = _salarioBase


    // El bloque 'init' se ejecuta al crear el ViewModel.
    init {
        cargarNominaMensual()
        cargarTotalBeneficio()
        cargarTotalDeduccion()
        cargarProyeccion()
        cargarSalarioBase()
        cargarDistribucionDepartamental()
    }

    /**
     * Elimina el prefijo "RD$" y cualquier espacio en blanco,
     * luego convierte el String resultante a Double.
     * Si la conversión falla, devuelve 0.0.
     */
    private fun limpiarYConvertir(valorConMoneda: String): Double {
        return valorConMoneda
            .replace("RD$", "", ignoreCase = true) // Quita el prefijo
            .trim() // Quita espacios
            .toDoubleOrNull() ?: 0.0 // Convierte a Double o devuelve 0.0 si falla
    }

    // --- Métodos de carga de datos ---

    private fun cargarProyeccion() {
        _proyeccion.value = null // Inicia en estado de carga
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getProyeccion()
                if (response.isSuccessful && !response.body().isNullOrEmpty()) {
                    val valorString = response.body()!![0].salarioBruto
                    _proyeccion.value = limpiarYConvertir(valorString) // Limpieza aplicada
                } else {
                    _proyeccion.value = 0.0
                }
            } catch (e: Exception) {
                _proyeccion.value = 0.0
                Log.e("NominaViewModel", "Error al cargar proyección", e)
            }
        }
    }


    private fun cargarTotalDeduccion() {
        _totalDeduccion.value = null // Inicia en estado de carga
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getTotalDeduccion()
                if (response.isSuccessful && !response.body().isNullOrEmpty()) {
                    val valorString = response.body()!![0].totalDeduccion
                    _totalDeduccion.value = limpiarYConvertir(valorString) // Limpieza aplicada
                } else {
                    _totalDeduccion.value = 0.0
                }
            } catch (e: Exception) {
                _totalDeduccion.value = 0.0
                Log.e("NominaViewModel", "Error al cargar deducción", e)
            }
        }
    }

    private fun cargarTotalBeneficio() {
        _totalBeneficio.value = null // Inicia en estado de carga
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getTotalBeneficio()
                if (response.isSuccessful && !response.body().isNullOrEmpty()) {
                    val valorString = response.body()!![0].totalBeneficio
                    _totalBeneficio.value = limpiarYConvertir(valorString) // Limpieza aplicada
                } else {
                    _totalBeneficio.value = 0.0
                }
            } catch (e: Exception) {
                _totalBeneficio.value = 0.0
                Log.e("NominaViewModel", "Error al cargar beneficio", e)
            }
        }
    }

    // --- Funciones que ya estaban correctas (sin cambios de lógica) ---

    private fun cargarNominaMensual() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getNominaMensual()
                if (response.isSuccessful && response.body() != null) {
                    _nominaMensual.value = response.body()
                } else {
                    _nominaMensual.value = emptyList()
                }
            } catch (e: Exception) {
                _nominaMensual.value = emptyList()
            }
        }
    }

    private fun cargarDistribucionDepartamental() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getDistribucionDepartamental()
                if (response.isSuccessful && response.body() != null) {
                    _distribucionDepartamental.value = response.body()
                } else {
                    _distribucionDepartamental.value = emptyList()
                    Log.e("NominaViewModel", "Error al cargar distribución: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                _distribucionDepartamental.value = emptyList()
                Log.e("NominaViewModel", "Error de red al cargar distribución", e)
            }
        }
    }

    fun cargarSalarioBase() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getSalarioBase()
                if (response.isSuccessful && response.body() != null) {
                    _salarioBase.value = response.body()
                    salarioB = response.body() ?: emptyList()
                } else {
                    _salarioBase.value = emptyList()
                    Log.e("SalarioBaseViewModel", "Error al cargar salario base ${response.errorBody()}")
                }
            } catch (e: Exception) {
                _salarioBase.value = emptyList()
                Log.e("SalarioBaseViewModel", "Error al cargar salario base", e)
            }
        }
    }
}
