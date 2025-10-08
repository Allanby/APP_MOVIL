// en com/example/api/viewmodels/ContractTypeViewModel.kt
package com.example.api.viewmodels // Asegúrate que el paquete sea el correcto

import TipoContrato
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.models.rh.ApiClient
import com.example.api.models.rh.RetrofitHelper
//import com.example.api.models.rh.TipoContrato // <- Importa el modelo correcto
import kotlinx.coroutines.launch

class ContractTypeViewModel : ViewModel() {

    // LiveData para los datos del gráfico de contratos
    private val _contractData = MutableLiveData<List<TipoContrato>>()
    val contractData: LiveData<List<TipoContrato>> = _contractData

    // LiveData para el estado de carga
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    // LiveData para los errores
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun loadContractData() {
        // Evita recargar si ya está cargando
        if (_loading.value == true) return
        _loading.value = true
        _error.value = null

        viewModelScope.launch {
            try {
                // Esta línea no es necesaria aquí si ya se estableció arriba, pero la mantenemos por consistencia
                _loading.value = true
                Log.d("ViewModelLoadContract", "Fetching contract types...")

                // Hacemos la llamada a la API usando la misma estructura que en GenderViewModel
                val response: List<TipoContrato> = RetrofitHelper.create().create(ApiClient::class.java).getContractTypes()

                // Actualizamos el LiveData con los datos obtenidos
                _contractData.postValue(response)
                Log.d("Data_Contract", "$response")

            } catch (e: Exception) {
                // Manejo de errores
                Log.e("ViewModelLoadContract", "Error fetching contract data: ${e.message}", e)
                _error.value = e.message ?: "Error de red"
                _contractData.postValue(emptyList()) // Envía una lista vacía en caso de error
                // El bloque 'finally' ya se encarga de esto, pero lo dejamos por consistencia con tu ejemplo
                _loading.value = false
            } finally {
                // Aseguramos que el estado de carga siempre termine
                _loading.value = false
                Log.d("ViewModelLoadContract", "Loading finished.")
            }
        }
    }
}
