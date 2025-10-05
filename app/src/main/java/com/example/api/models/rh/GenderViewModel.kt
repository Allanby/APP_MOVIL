// en com/example/api/GenderViewModel.kt
package com.example.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.models.rh.ApiClient
import com.example.api.models.rh.GenderCount // Asegúrate de importar tu modelo
import com.example.api.models.rh.RetrofitHelper
import kotlinx.coroutines.launch
import java.io.IOException

class GenderViewModel : ViewModel() {

    // LiveData para los datos procesados del gráfico.
    // Usaremos un Pair<Int, Int> para guardar (Hombres, Mujeres).
    private val _genderData = MutableLiveData<Pair<Int, Int>?>()
    val genderData: LiveData<Pair<Int, Int>?> = _genderData

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


                val response: ArrayList<GenderCount> = RetrofitHelper.create().create(
                    ApiClient::class.java
                ).getCount()

                // ✅ Procesar la respuesta
                val hombres = response.find { it.gender == "M" }?.total ?: 0
                val mujeres = response.find { it.gender == "F" }?.total ?: 0

                // ✅ Publicar el resultado procesado en LiveData
                _genderData.postValue(Pair(hombres, mujeres))

                Log.d("ViewModelLoadGender", "Datos procesados: Hombres=$hombres, Mujeres=$mujeres")

            } catch (e: Exception) {
                Log.e("ViewModelLoadGender", "Error fetching gender data: ${e.message}", e)
                _error.value = e.message ?: "Error de red"
                _genderData.postValue(Pair(0, 0))
                _loading.value = false
            } finally {
                _loading.value = false
                Log.d("ViewModelLoadGender", "Loading finished.")
            }
        }
    }}
