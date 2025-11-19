package com.example.api.viewmodels

import TipoContrato
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.models.rh.ApiClient
import com.example.api.models.rh.RetrofitHelper
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
                _loading.value = true
                Log.d("ViewModelLoadContract", "Fetching contract types...")

                val response: List<TipoContrato> = RetrofitHelper.create().create(ApiClient::class.java).getContractTypes()

                _contractData.postValue(response)
                Log.d("Data_Contract", "$response")

            } catch (e: Exception) {
                // Manejo de errores
                Log.e("ViewModelLoadContract", "Error fetching contract data: ${e.message}", e)
                _error.value = e.message ?: "Error de red"
                _contractData.postValue(emptyList())
                _loading.value = false
            } finally {
                _loading.value = false
                Log.d("ViewModelLoadContract", "Loading finished.")
            }
        }
    }
}
