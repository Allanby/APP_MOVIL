package com.example.adminrh // O el paquete donde esté tu fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.fragment.app.Fragment
import com.example.gerencia.GerenciaScreen
// 👇 ¡AHORA SÍ FUNCIONARÁ! Importa la función del tema que acabas de crear.
import com.example.adminrh.ui.theme.AdminrhTheme
import com.example.gerencia.components.TarjetaInformativa

class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            // Estrategia para evitar fugas de memoria
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            // Establece el contenido de Compose
            setContent {
                // ✅ Envuelve tu pantalla en el tema de Compose
                AdminrhTheme {
                    //GerenciaScreen()
                    Image(
                        painter = painterResource(id = R.drawable.logo_admin_rh),
                        contentDescription = "Logo",

                    )
                }
            }
        }
    }
}

class CargoFragment(): Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            // Estrategia para evitar fugas de memoria
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            // Establece el contenido de Compose
            setContent {
                // ✅ Envuelve tu pantalla en el tema de Compose
                AdminrhTheme {
                    //GerenciaScreen()
                    TarjetaInformativa(titulo = "Cargos", cantidad = 25, icon = R.drawable.ic_work_24)
                }
            }
        }
    }
}

class JornadaFragment(): Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            // Estrategia para evitar fugas de memoria
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            // Establece el contenido de Compose
            setContent {
                // ✅ Envuelve tu pantalla en el tema de Compose
                AdminrhTheme {
                    //GerenciaScreen()
                    TarjetaInformativa(titulo = "Jornadas", cantidad = 3, icon = R.drawable.ic_calendar_clock_24)
                }
            }
        }
    }
}

class DepartamentoFragment(): Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            // Estrategia para evitar fugas de memoria
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            // Establece el contenido de Compose
            setContent {
                // ✅ Envuelve tu pantalla en el tema de Compose
                AdminrhTheme {
                    //GerenciaScreen()
                    TarjetaInformativa(titulo = "Departamentos", cantidad = 12, icon = R.drawable.ic_apartment_24)
                }
            }
        }
    }
}
