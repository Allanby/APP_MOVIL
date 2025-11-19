package com.example.adminrh.nomina.reportes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nomina.retrofit.RetrofitClient
import com.example.api.models.nomina.NominaMensual
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.lineSeries
import kotlinx.coroutines.launch

sealed class ReportesNominaUiState {
    object Loading : ReportesNominaUiState()
    data class Success(val producer: CartesianChartModelProducer) : ReportesNominaUiState()
    data class Error(val message: String) : ReportesNominaUiState()
}

class ReportesNominaViewModel : ViewModel() {

    private val _uiState = mutableStateOf<ReportesNominaUiState>(ReportesNominaUiState.Loading)
    val uiState: State<ReportesNominaUiState> = _uiState

    private val modelProducer = CartesianChartModelProducer()

    init {
        cargarDatosNominaMensual()
    }

    private fun cargarDatosNominaMensual() {
        viewModelScope.launch {
            _uiState.value = ReportesNominaUiState.Loading
            try {
                val response = RetrofitClient.apiService.getNominaMensual()

                if (response.isSuccessful && response.body() != null) {
                    val datosApi = response.body()!!
                    actualizarDatosGrafico(datosApi)
                    _uiState.value = ReportesNominaUiState.Success(modelProducer)
                } else {
                    _uiState.value = ReportesNominaUiState.Error("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                _uiState.value = ReportesNominaUiState.Error("Error de conexión: ${e.message}")
            }
        }
    }

    private suspend fun actualizarDatosGrafico(datos: List<NominaMensual>) {
        modelProducer.runTransaction {
            lineSeries {
                series(datos.map { it.salarioBruto })
            }
        }
    }
}
