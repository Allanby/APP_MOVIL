package com.example.api.models.rh

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class empleadosViewModel : ViewModel() {
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _totalEmpleadosActivos = MutableLiveData<Int?>()
    val totalEmpleadosActivos: LiveData<Int?> = _totalEmpleadosActivos


    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    @SuppressLint("SuspiciousIndentation")
    fun load() {
        if (loading.value == true) return
        _loading.value = true
        _error.value = null
        viewModelScope.launch {
            try {
                _loading.value = true
                Log.d("ViewModelLoad", "Fetching employees...")
                val response = RetrofitHelper.create().create(ApiClient::class.java).getEmployees() // MAKE SURE THIS RETURNS List<Employee>
                _totalEmpleadosActivos.value = response.total
                Log.d("Data","${_totalEmpleadosActivos.value}")
            } catch (e: Exception) {
                Log.e("ViewModelLoad", "Error fetching employees: ${e.message}", e)
                _error.value = e.message ?: "Error de red"
               _totalEmpleadosActivos.value = null
                _loading.value = false
            } finally {
                _loading.value = false
                Log.d("ViewModelLoad", "Loading finished.")
            }
        }
    }

    // api para obtener empleados tipo de contrato


}