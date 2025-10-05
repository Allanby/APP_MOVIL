// en com/example/api/GenderViewModel.kt
package com.example.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.models.rh.ApiClient
import com.example.api.models.rh.GenderCount
import com.example.api.models.rh.RetrofitHelper
import kotlinx.coroutines.launch

class GenderViewModel : ViewModel() {

    // LiveData para los datos procesados del gráfico.
    // Usaremos un Pair<Int, Int> para guardar (Hombres, Mujeres).
    private val _genderData = MutableLiveData<List<GenderCount>>()
    val genderData: LiveData<List<GenderCount>> = _genderData

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun loadGenderData() {
        if (_loading.value == true) return
        _loading.value = true
        _error.value = null

        viewModelScope.launch {
            try {
                _loading.value = true
                Log.d("ViewModelLoadGender", "Fetching gender distribution...")


                val response: List<GenderCount> = RetrofitHelper.create().create(ApiClient::class.java).getGenderDistribution()
                _genderData.postValue(response)
                Log.d("Data","$response")

                //Log.d("ViewModelLoadGender", "Datos procesados: Hombres=$hombres, Mujeres=$mujeres")

            } catch (e: Exception) {
                Log.e("ViewModelLoadGender", "Error fetching gender data: ${e.message}", e)
                _error.value = e.message ?: "Error de red"
                _genderData.postValue(emptyList())
                _loading.value = false
            } finally {
                _loading.value = false
                Log.d("ViewModelLoadGender", "Loading finished.")
            }
        }
    }}
