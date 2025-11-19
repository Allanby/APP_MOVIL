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

// Estados de la UI para la pantalla de reportes
sealed class ReportesNominaUiState {
    object Loading : ReportesNominaUiState()
    data class Success(val producer: CartesianChartModelProducer) : ReportesNominaUiState()
    data class Error(val message: String) : ReportesNominaUiState()
}

class ReportesNominaViewModel : ViewModel() {

    // LiveData o State para observar el estado desde la UI de Compose
    private val _uiState = mutableStateOf<ReportesNominaUiState>(ReportesNominaUiState.Loading)
    val uiState: State<ReportesNominaUiState> = _uiState

    // Productor de datos para el gráfico Vico
    private val modelProducer = CartesianChartModelProducer()

    init {
        // Cargar los datos al iniciar el ViewModel
        cargarDatosNominaMensual()
    }

    private fun cargarDatosNominaMensual() {
        viewModelScope.launch {
            _uiState.value = ReportesNominaUiState.Loading
            try {
                val response = RetrofitClient.apiService.getNominaMensual()

                if (response.isSuccessful && response.body() != null) {
                    val datosApi = response.body()!!
                    // Preparamos los datos para el gráfico
                    actualizarDatosGrafico(datosApi)
                    _uiState.value = ReportesNominaUiState.Success(modelProducer)
                } else {
                    _uiState.value = ReportesNominaUiState.Error("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                // Manejo de errores de conexión, etc.
                _uiState.value = ReportesNominaUiState.Error("Error de conexión: ${e.message}")
            }
        }
    }

    private suspend fun actualizarDatosGrafico(datos: List<NominaMensual>) {
        // Transforma los datos de la API en una serie para el gráfico de Vico
        modelProducer.runTransaction {
            // Mapeamos cada 'salarioBruto' a un punto en el gráfico
            lineSeries {
                series(datos.map { it.salarioBruto })
            }
        }
    }
}
