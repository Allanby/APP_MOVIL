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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textPermisos = view.findViewById(R.id.textPermisosMes)
        textPromedioDias = view.findViewById(R.id.textPromedioDias)

        fetchPermisos()
        fetchPromedioPermisos()
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
}

