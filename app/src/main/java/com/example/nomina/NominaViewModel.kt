package com.example.adminrh.nomina

import DatosNomina
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

// Define una clase sellada para manejar los diferentes estados de la UI
sealed class NominaEstado {
    object Cargando : NominaEstado()
    data class Exito(val datosNomina: DatosNomina) : NominaEstado()
    data class Error(val mensaje: String) : NominaEstado()
}

class NominaViewModel : ViewModel() {

    // Usa LiveData (o StateFlow si prefieres un enfoque más moderno de corrutinas)
    private val _estado = MutableLiveData<NominaEstado>()
    val estado: LiveData<NominaEstado> = _estado

    init {
        // Carga los datos iniciales al crear el ViewModel
        // cargarDatosNomina(2025, 10) // Año y mes actual, por ejemplo
    }

    fun cargarDatosNomina(anio: Int, mes: Int) {
        viewModelScope.launch {
            _estado.value = NominaEstado.Cargando
            try {
                // --- AQUÍ LLAMARÁS A TU API CON RETROFIT ---
                // val respuestaApi = miServicioApiDeNomina.getDatos(anio, mes)
                // _estado.value = NominaEstado.Exito(respuestaApi)

                // Por ahora, simulamos un error para que veas cómo se manejaría
                _estado.value = NominaEstado.Error("APIs no implementadas todavía.")

            } catch (e: Exception) {
                _estado.value = NominaEstado.Error("Error al cargar los datos: ${e.message}")
            }
        }
    }
}
