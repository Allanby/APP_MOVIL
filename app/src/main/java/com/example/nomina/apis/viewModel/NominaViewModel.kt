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
// Importa el RetrofitClient que estás usando en tu otro ViewModel.
// ¡Asegúrate de que esta ruta es la correcta para tu proyecto!
import com.example.nomina.retrofit.RetrofitClient
import kotlinx.coroutines.launch


sealed class NominaEstado {
    object Cargando : NominaEstado()
    // La referencia a DatosNomina puede que necesite un import si está en otro paquete
    // data class Exito(val datosNomina: com.example.api.models.nomina.DatosNomina) : NominaEstado()
    data class Error(val mensaje: String) : NominaEstado()
}

class NominaViewModel : ViewModel() {

    // --- LiveData para el total de beneficios ---
    // Usamos MutableLiveData para poder cambiar su valor desde aquí.

    private val _totalBeneficio = MutableLiveData<Double?>()
    // Y exponemos un LiveData inmutable a la UI para que solo pueda observarlo.
    val totalBeneficio: LiveData<Double?> = _totalBeneficio

    //  LiveData para el total de deducciones
    private val _totalDeduccion = MutableLiveData<Double?>()
    val totalDeduccion: LiveData<Double?> = _totalDeduccion

    private val _nominaMensual = MutableLiveData<List<NominaMensual>>()
    val nominaMensual: LiveData<List<NominaMensual>> = _nominaMensual

    private val _salarioBase = MutableLiveData<List<SalarioBase>>()
    var salarioB by mutableStateOf(emptyList<SalarioBase>())
        private set
    val salarioBase: LiveData<List<SalarioBase>> = _salarioBase


    init {
        // Llamamos a la función para cargar los datos de la API.
        cargarNominaMensual()
    }
    private val _proyeccion = MutableLiveData<Double?>()
    val proyeccion: LiveData<Double?> = _proyeccion

    private val _distribucionDepartamental = MutableLiveData<List<DistribucionDepartamental>>()
    val distribucionDepartamental: LiveData<List<DistribucionDepartamental>> = _distribucionDepartamental





    private fun cargarNominaMensual() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getNominaMensual()
                if (response.isSuccessful && response.body() != null) {
                    _nominaMensual.value = response.body()
                } else {
                    _nominaMensual.value = emptyList() // Enviar lista vacía en caso de error
                }
            } catch (e: Exception) {
                _nominaMensual.value = emptyList() // Enviar lista vacía en caso de error
            }
        }
    }


    // El bloque 'init' se ejecuta en cuanto se crea una instancia del ViewModel.
    init {
        // Llamamos a la función para cargar los datos de la API.
        cargarTotalBeneficio()

    }
    init {
        // Llamamos a la función para cargar los datos de la API.
        cargarTotalDeduccion()
    }
    init {
        // Llamamos a la función para cargar los datos de la API.
        cargarProyeccion()
    }
    init {
        // Llamamos a la función para cargar los datos de la API.
        cargarSalarioBase()
    }
    init {
        cargarDistribucionDepartamental()

    }

    private fun cargarDistribucionDepartamental() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getDistribucionDepartamental()
                if (response.isSuccessful && response.body() != null) {
                    _distribucionDepartamental.value = response.body()
                    Log.d("NominaViewModel", "Distribución cargada: ${response.body()}")
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
                    Log.d("SalarioBaseViewModel", "SalarioBase: ${response.body()}")
                } else {
                    _salarioBase.value = emptyList() // Enviar lista vacía en caso de error
                    Log.e("SalarioBaseViewModel", "Error al cargar salario base ${response.errorBody()}")
                }
            } catch (e: Exception) {
                _salarioBase.value = emptyList() // Enviar lista vacía en caso de error
                Log.e("SalarioBaseViewModel", "Error al cargar salario base", e)
            }
        }

    }

    private fun cargarProyeccion() {
        _proyeccion.value = null // Inicia en estado de carga
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getProyeccion()
                if (response.isSuccessful && response.body()?.isNotEmpty() == true) {
                    // La API devuelve una lista, tomamos el primer valor
                    _proyeccion.value = response.body()!![0].salarioBruto
                } else {
                    _proyeccion.value = 0.0 // Valor por defecto en caso de error
                }
            } catch (e: Exception) {
                _proyeccion.value = 0.0 // Valor por defecto en caso de error
            }
        }
    }

    private fun cargarTotalDeduccion() {
        _totalDeduccion.value = null // Inicia en estado de carga
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getTotalDeduccion()
                if (response.isSuccessful && response.body()?.isNotEmpty() == true) {
                    _totalDeduccion.value = response.body()!![0].totalDeduccion
                } else {
                    _totalDeduccion.value = 0.0 // Valor por defecto en caso de error
                }
            } catch (e: Exception) {
                _totalDeduccion.value = 0.0 // Valor por defecto en caso de error
            }
        }

    }


    /**
     * Llama a la API para obtener el total de beneficios y actualiza el LiveData.
     */
    private fun cargarTotalBeneficio() {
        // Inicia el valor como nulo para que la UI pueda mostrar un estado de "Cargando..."
        _totalBeneficio.value = null

        // Usamos viewModelScope para lanzar una corrutina que se cancelará automáticamente
        // si el ViewModel es destruido, evitando fugas de memoria.
        viewModelScope.launch {
            try {
                // Llamamos a la función de la API a través de nuestro cliente Retrofit.
                val response = RetrofitClient.apiService.getTotalBeneficio()

                // Verificamos si la llamada fue exitosa (código 2xx) y si el cuerpo no es nulo o vacío.
                if (response.isSuccessful && !response.body().isNullOrEmpty()) {
                    // La API devuelve una lista, así que tomamos el primer (y único) objeto.
                    // Actualizamos el valor del LiveData. La UI que lo observe reaccionará a este cambio.
                    _totalBeneficio.value = response.body()!![0].totalBeneficio
                } else {
                    // Si la respuesta no fue exitosa, ponemos un valor por defecto para indicar error.
                    _totalBeneficio.value = 0.0
                }
            } catch (e: Exception) {
                // Si ocurre un error de red (sin conexión, timeout, etc.), lo capturamos.
                // También ponemos un valor por defecto.
                _totalBeneficio.value = 0.0
            }
        }
    }
}
