package com.example.api

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.models.rh.ApiClient
import com.example.api.models.rh.EmployeeDepartamentResponse
import com.example.api.models.rh.RetrofitHelper
import kotlinx.coroutines.launch

class departamentosViewModel : ViewModel() {
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private var _totalEmpleadosDepartamento =   MutableLiveData<ArrayList<EmployeeDepartamentResponse>>()
    var totalEmpleadosDepartamento: LiveData<ArrayList<EmployeeDepartamentResponse>> = _totalEmpleadosDepartamento


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
                Log.d("ViewModelLoadDepartamento", "Fetching departamento...")
                val response: ArrayList<EmployeeDepartamentResponse> = RetrofitHelper.create().create(
                    ApiClient::class.java).getEmployeesByDepartment()
                _totalEmpleadosDepartamento.postValue(response)
                Log.d("Data","$response")
            } catch (e: Exception) {
                Log.e("ViewModelLoad", "Error fetching employees: ${e.message}", e)
                _error.value = e.message ?: "Error de red"
                _totalEmpleadosDepartamento.postValue(arrayListOf())
                _loading.value = false
            } finally {
                _loading.value = false
                Log.d("ViewModelLoad", "Loading finished.")
            }
        }
    }


}

