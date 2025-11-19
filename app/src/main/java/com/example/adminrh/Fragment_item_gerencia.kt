package com.example.adminrh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.fragment.app.Fragment
import com.example.gerencia.ui.GerenciaScreen
import com.example.adminrh.ui.theme.AdminrhTheme
import com.example.gerencia.components.TarjetaInformativa

class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            setContent {
                AdminrhTheme {
                    GerenciaScreen()
//
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
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            setContent {
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
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            setContent {
                AdminrhTheme {
                    //()
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
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            setContent {
                AdminrhTheme {
                    GerenciaScreen()
                }
            }
        }
    }
}
