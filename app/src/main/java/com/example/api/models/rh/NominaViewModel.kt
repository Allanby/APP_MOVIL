package com.example.api.models.rh
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.models.rh.NominaListResponce_Compo
import com.example.api.models.rh.retrofitPrueba
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class  NominaViewModel : ViewModel() {

    val _nominas = MutableStateFlow<List<NominaListResponce_Compo>>(emptyList())
    val nominas: StateFlow<List<NominaListResponce_Compo>> = _nominas

    fun fetchNominas() {
        viewModelScope.launch {
            try {
                val response = retrofitPrueba.api.getNominas()
                _nominas.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}