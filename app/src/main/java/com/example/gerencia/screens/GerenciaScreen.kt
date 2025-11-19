package com.example.gerencia.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.adminrh.R
import com.example.gerencia.components.TarjetaInformativa
import com.example.gerencia.viewModel.CargosUiState
import com.example.gerencia.viewModel.DepartamentosUiState
import com.example.gerencia.viewModel.GerenciaViewModel
import com.example.gerencia.viewModel.JornadasUiState

// --- Función principal que se conecta a la navegación (sin cambios) ---
@Composable
fun GerenciaScreen(
    gerenciaViewModel: GerenciaViewModel = viewModel()
) {
    val departamentosState by gerenciaViewModel.departamentosUiState.collectAsState()
    val jornadasState by gerenciaViewModel.jornadasUiState.collectAsState()
    val cargosState by gerenciaViewModel.cargosUiState.collectAsState()

    GerenciaContent(
        departamentosState = departamentosState,
        jornadasState = jornadasState,
        cargosState = cargosState
    )
}

@Composable
fun GerenciaContent(
    departamentosState: DepartamentosUiState,
    jornadasState: JornadasUiState,
    cargosState: CargosUiState
) {
    Column(
        modifier = Modifier
            .fillMaxSize() // La columna ocupa todo el espacio
            .padding(16.dp), // Un padding general para que no esté pegado a los bordes
        verticalArrangement = Arrangement.spacedBy(16.dp) // Espacio vertical entre las tarjetas
    ) {
        // ---------- DEPARTAMENTOS ----------
        // 2. Se añade weight(1f) para que cada tarjeta ocupe el mismo espacio vertical
        TarjetaContainer(modifier = Modifier.weight(1f)) {
            when {
                departamentosState.isLoading -> {
                    CircularProgressIndicator()
                }
                departamentosState.error != null -> {
                    Text(text = departamentosState.error, color = Color.Red)
                }
                else -> {
                    TarjetaInformativa(
                        titulo = "Departamentos",
                        cantidad = departamentosState.cantidad,
                        icon = R.drawable.ic_launcher_foreground // Reemplaza con tu ícono
                    )
                }
            }
        }

        // ---------- JORNADAS ----------
        TarjetaContainer(modifier = Modifier.weight(1f)) {
            when {
                jornadasState.isLoading -> {
                    CircularProgressIndicator()
                }
                jornadasState.error != null -> {
                    Text(text = jornadasState.error, color = Color.Red)
                }
                else -> {
                    TarjetaInformativa(
                        titulo = "Jornadas",
                        cantidad = jornadasState.cantidad,
                        icon = R.drawable.ic_calendar_clock_24
                    )
                }
            }
        }

        // ---------- CARGOS ----------
        TarjetaContainer(modifier = Modifier.weight(1f)) {
            when {
                cargosState.isLoading -> {
                    CircularProgressIndicator()
                }
                cargosState.error != null -> {
                    Text(text = cargosState.error, color = Color.Red)
                }
                else -> {
                    TarjetaInformativa(
                        titulo = "Cargos",
                        cantidad = cargosState.cantidad,
                        icon = R.drawable.ic_work_24
                    )
                }
            }
        }
    }
}

@Composable
private fun TarjetaContainer(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier.fillMaxWidth(), // El contenedor ocupa todo el ancho
        contentAlignment = Alignment.Center,
        content = content
    )
}


@Preview(showBackground = true, name = "Dashboard Completo")
@Composable
fun GerenciaPreview_Success() {
    GerenciaContent(
        departamentosState = DepartamentosUiState(cantidad = 15),
        jornadasState = JornadasUiState(cantidad = 7),
        cargosState = CargosUiState(cantidad = 21)
    )
}
