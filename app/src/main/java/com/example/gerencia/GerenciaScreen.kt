package com.example.gerencia

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gerencia.components.TarjetaInformativa // 👈 Importamos nuestro nuevo Composable

@Composable
fun GerenciaScreen() {
    // Usamos LazyVerticalGrid para que la pantalla sea adaptable y tenga scroll si hay muchos elementos.
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp), // Las columnas se adaptan al tamaño de la pantalla
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // En un futuro, estos datos vendrán de un ViewModel o una API.
        // Por ahora, usamos valores de ejemplo.
        item {
           // TarjetaInformativa(titulo = "Cargos", cantidad = 25)
        }
        item {
         //   TarjetaInformativa(titulo = "Jornadas", cantidad = 3)
        }
        item {
           // TarjetaInformativa(titulo = "Departamentos", cantidad = 12)
        }

        // Puedes agregar más tarjetas aquí si lo necesitas
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun GerenciaScreenPreview() {
    GerenciaScreen()
}
