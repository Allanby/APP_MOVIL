package com.example.admin_rh

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.adminrh.R
import com.example.api.models.rh.retrofitPrueba
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScrollingFragmentPermisos : Fragment(R.layout.fragment_scrolling_permisos) {

    private lateinit var textPermisos: TextView
    private lateinit var textPromedioDias: TextView
    private lateinit var textDiasPerdidos: TextView
    private lateinit var textGeneroTotal: TextView
    private lateinit var textDepartamentoTotal: TextView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textPermisos = view.findViewById(R.id.textPermisosMes)
        textPromedioDias = view.findViewById(R.id.textPromedioDias)
        textDiasPerdidos = view.findViewById(R.id.textDiasPerdidos)
        textGeneroTotal = view.findViewById(R.id.textGeneroTotal)
        textDepartamentoTotal = view.findViewById(R.id.textDepartamentoTotal)

        fetchPermisos()
        fetchPromedioPermisos()
        fetchDiasPerdidos()
        fetchGeneroMasPermisos()
        fetchDepartamentoPopular()
    }

    //permisos por mes
    private fun fetchPermisos() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitPrueba.api.getPermisosMes()

                withContext(Dispatchers.Main) {
                    textPermisos.text = "Permisos del mes: ${response.permisos_mes_actual}"
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    textPermisos.text = getString(R.string.errormensaje)
                }
            }
        }
    }



    //promedio de duracion de los permisos
    private fun fetchPromedioPermisos() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitPrueba.api.getPromedioPermisos()
                withContext(Dispatchers.Main) {
                    textPromedioDias.text = "Promedio: ${response.promedio_dias} días"
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    textPromedioDias.text = getString(R.string.errormensaje)
                }
            }
        }
    }

    //dias perdidos

    private fun fetchDiasPerdidos() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitPrueba.api.getDiasPerdidos()
                withContext(Dispatchers.Main) {
                    textDiasPerdidos.text = "${response.dias_perdidos} días"
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    textDiasPerdidos.text = getString(R.string.errormensaje)
                }
            }
        }
    }


    //genemo con mas permisos
    private fun fetchGeneroMasPermisos() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitPrueba.api.getPermisosGenero()
                withContext(Dispatchers.Main) {
                    val generoNombre = if(response.genero == "M") "Masculino" else "Femenino"
                    textGeneroTotal.text = "$generoNombre - ${response.total_permisos}"
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    textGeneroTotal.text =  getString(R.string.errormensaje)
                }
            }
        }
    }

    //Departamento con mpas permisos
    private fun fetchDepartamentoPopular() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitPrueba.api.getDepartamentoPermisos()
                withContext(Dispatchers.Main) {
                    // Actualiza la card con los datos
                    textDepartamentoTotal.text = "${response.departamento} - ${response.total_permisos}"
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    textDepartamentoTotal.text = getString(R.string.errormensaje)
                }
            }
        }
    }

}


