package com.example.gerencia.ui.gerencia

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

    val departamentosState by gerenciaViewModel.departamentosUiState.collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 180.dp),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Tarjeta de Departamentos
        item {
            if (departamentosState.isLoading) {
                // indicador de carga
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else if (departamentosState.error != null) {
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

                TarjetaInformativa(
                    titulo = "Departamentos",
                    cantidad = departamentosState.cantidad,
                    icon = R.drawable.ic_launcher_foreground
                )
            }
        }
        item {
        }
        item {

        }

    }
}

@Preview(showBackground = true)
@Composable
fun GerenciaScreenPreview() {

    GerenciaScreen()
}

