package com.example.admin_rh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.example.adminrh.nomina.reportes.ReportesNominaScreen

class Fragment_item_reportes_nomina : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            // Estrategia para destruir la composición cuando la vista del Fragment se destruye
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            // Establece el contenido de Compose
            setContent {
                // Aquí usamos el Composable que acabamos de crear
                ReportesNominaScreen()
            }
        }
    }

    // El resto del código (companion object, etc.) puede permanecer como está.
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment_item_reportes_nomina().apply {
                arguments = Bundle().apply {
                    // Si necesitas pasar argumentos, hazlo aquí
                }
            }
    }
}
