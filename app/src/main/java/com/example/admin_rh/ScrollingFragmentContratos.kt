package com.example.admin_rh
import com.example.adminrh.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.coroutines.launch
import android.widget.TextView
import com.example.api.models.rh.retrofitPrueba
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ScrollingFragmentContratos : Fragment(R.layout.fragment_scrolling_contratos) {
    private lateinit var textContratosPromedio: TextView
    private lateinit var textContratosNumero: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textContratosPromedio = view.findViewById(R.id.textContratosPromedio)
        textContratosNumero = view.findViewById(R.id.textContratosNumero)
        fetchPromedioMeses()
        fetchContratosVigentes()
    }

    private fun fetchPromedioMeses() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitPrueba.api.getPromedioMeses()

                withContext(Dispatchers.Main) {
                    textContratosPromedio.text = "Meses: ${response.meses_promedio}"
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    textContratosPromedio.text = getString(R.string.errormensaje)
                }
            }
        }
    }

    //contratos vigentes

    private fun fetchContratosVigentes() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitPrueba.api.getContratosVigentes()
                withContext(Dispatchers.Main) {
                    textContratosNumero.text = response.contratos_vigentes.toString()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    textContratosNumero.text = getString(R.string.errormensaje)
                }
            }
        }
    }

}