package com.example.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.models.rh.AgeRangeCount
import com.example.api.models.rh.ApiClient
import kotlinx.coroutines.launch
import java.io.IOException
import com.example.api.models.rh.RetrofitHelper
import retrofit2.create

class AgeViewModel : ViewModel() {

    private val _ageData = MutableLiveData<List<AgeRangeCount>?>()
    val ageData: LiveData<List<AgeRangeCount>?> = _ageData

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun loadAgeData() {
        if (_loading.value == true) return

        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                // Llama al nuevo método en tu ApiService
                val response = RetrofitHelper.create().create(ApiClient::class.java).getAgeDistribution()
                _ageData.value = response
                Log.d("AgeViewModel", "Datos de edad recibidos: ${response.size} rangos.")

            } catch (e: IOException) {
                _error.value = "Error de red: No se pudo conectar al servidor."
                Log.e("AgeViewModel", "IOException: ${e.message}", e)
            } catch (e: Exception) {
                _error.value = "Error inesperado al cargar los datos de edad."
                Log.e("AgeViewModel", "Exception: ${e.message}", e)
            } finally {
                _loading.value = false
                Log.d("AgeViewModel", "Loading finished.")
            }

        }
    }
}
