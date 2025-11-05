package com.example.adminrh.nomina

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
// 👇 IMPORTACIONES CORREGIDAS: Apuntando a la ubicación correcta de tus componentes y ViewModel
import com.example.adminrh.nomina.components.SelectorDeMes
import com.example.adminrh.nomina.components.TarjetaInformativa
import com.example.nomina.Graficos.GraficoDepartamental

// Asumimos que NominaViewModel también está en el paquete 'com.example.adminrh.nomina'
// Si no, ajusta esta importación.

@Composable
fun NominaScreen(nominaViewModel: NominaViewModel = viewModel()) {
    // Observamos el estado desde el ViewModel
    // val estadoNomina by nominaViewModel.estado.observeAsState(NominaEstado.Cargando)

    Scaffold { padding ->
        // Usamos LazyColumn para que la pantalla sea scrollable si el contenido es mucho
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp) // Espacio entre cada elemento
        ) {
            // 1. Selector de Mes
            item {
                SelectorDeMes(
                    mesActual = "Octubre 2025",
                    onMesSiguiente = { /* TODO: Lógica del ViewModel */ },
                    onMesAnterior = { /* TODO: Lógica del ViewModel */ }
                )
                // Espacio entre el selector y las tarjetas
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    TarjetaInformativa(
                        titulo = "Proyección",
                        valor = "$150,000.00", // Dato de ejemplo
                        modifier = Modifier.weight(1f)
                    )
                    TarjetaInformativa(
                        titulo = "Deducciones",
                        valor = "$25,000.00", // Dato de ejemplo
                        modifier = Modifier.weight(1f)
                    )
                }
                // Espacio entre las tarjetas y el gráfico
                Spacer(modifier = Modifier.height(16.dp))
                TarjetaInformativa(
                    titulo = "Beneficios",
                    valor = "$5,000.00", // Dato de ejemplo
                    modifier = Modifier.fillMaxWidth()
                )
                // Espacio entre las tarjetas y el gráfico
                Spacer(modifier = Modifier.height(16.dp))
                GraficoDepartamental()


            }



            // --- EJEMPLO DE MANEJO DE ESTADO (cuando integres el ViewModel) ---
            /*
            when (val estado = estadoNomina) {
                is NominaEstado.Cargando -> {
                    item { androidx.compose.material3.CircularProgressIndicator() }
                }
                is NominaEstado.Exito -> {
                   // Aquí construirías los items con los datos de 'estado.datosNomina'
                }
                is NominaEstado.Error -> {
                    item { androidx.compose.material3.Text(text = estado.mensaje) }
                }
            }
            */
        }
    }
}

// Preview para ver el diseño sin ejecutar la app
@Preview(showBackground = true)
@Composable
fun NominaScreenPreview() {
    NominaScreen()
}
