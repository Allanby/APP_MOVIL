package com.example.gerencia.ui.gerencia // Paquete actualizado

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import com.example.gerencia.viewModel.GerenciaViewModel

@Composable
fun GerenciaScreen(
    gerenciaViewModel: GerenciaViewModel = viewModel() // Inyecta el ViewModel
) {
    // Observa el estado de los departamentos
    val departamentosState by gerenciaViewModel.departamentosUiState.collectAsState()

    // Usamos LazyVerticalGrid para que la pantalla sea adaptable y tenga scroll si hay muchos elementos.
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 180.dp), // Ajuste para mejor visualización
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp), // Un padding más consistente
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Tarjeta de Departamentos
        item {
            if (departamentosState.isLoading) {
                // Muestra un indicador de carga mientras los datos llegan
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp) // Misma altura que la tarjeta
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else if (departamentosState.error != null) {
                // Muestra un mensaje de error si algo falló
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = departamentosState.error!!, color = Color.Red)
                }
            } else {
                // Muestra la tarjeta cuando los datos están listos
                TarjetaInformativa(
                    titulo = "Departamentos",
                    cantidad = departamentosState.cantidad,
                    icon = R.drawable.ic_launcher_foreground // Reemplaza con un ícono adecuado
                )
            }
        }
        item {
            // TarjetaInformativa(titulo = "Cargos", cantidad = 25, icon = R.drawable.ic_launcher_foreground)
        }
        item {
            //   TarjetaInformativa(titulo = "Jornadas", cantidad = 3, icon = R.drawable.ic_launcher_foreground)
        }

        // Puedes agregar más tarjetas aquí si lo necesitas
    }
}

@Preview(showBackground = true)
@Composable
fun GerenciaScreenPreview() {
    // El preview no puede usar el ViewModel real, así que lo dejamos simple por ahora.
    // O podrías crear un ViewModel falso para el preview.
    GerenciaScreen()
}

