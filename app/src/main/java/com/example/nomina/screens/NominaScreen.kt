package com.example.adminrh.nomina

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.MoneyOff
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.adminrh.nomina.components.SelectorDeMes
import com.example.adminrh.nomina.components.TarjetaInformativa
import com.example.adminrh.nomina.ui.DistribucionDepartamentalCards
import com.example.nomina.Graficos.GraficoSalarioBase
import java.text.NumberFormat
import java.util.Locale

@Composable
fun NominaScreen(nominaViewModel: NominaViewModel = viewModel()) {
    val totalBeneficio by nominaViewModel.totalBeneficio.observeAsState(initial = null)
    val totalDeduccion by nominaViewModel.totalDeduccion.observeAsState(initial = null)
    val proyeccion by nominaViewModel.proyeccion.observeAsState(initial = null)

    val currencyFormatter = remember { NumberFormat.getCurrencyInstance(Locale("es", "DO")) }

    Scaffold { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Selector de Mes
            item {
                SelectorDeMes(mesActual = "Octubre 2025")
            }

            // Tarjeta Proyección
            item {
                TarjetaInformativa(
                    titulo = "Proyección",
                    valor = proyeccion?.let { currencyFormatter.format(it) } ?: "Cargando...",
                    modifier = Modifier.fillMaxWidth(),
                    colorFondo = Color(0xDD5D025F),
                    colorIcono = Color.White,
                    icono = Icons.Default.AttachMoney
                )
            }

            // Tarjeta Deducciones
            item {
                TarjetaInformativa(
                    titulo = "Deducciones",
                    valor = totalDeduccion?.let { currencyFormatter.format(it) } ?: "Cargando...",
                    modifier = Modifier.fillMaxWidth(),
                    colorFondo = Color(0xFF044B2A),
                    colorIcono = Color.White,
                    icono = Icons.Default.MoneyOff
                )
            }

            // Tarjeta Beneficios
            item {
                TarjetaInformativa(
                    titulo = "Beneficios",
                    valor = totalBeneficio?.let { currencyFormatter.format(it) } ?: "Cargando...",
                    modifier = Modifier.fillMaxWidth(),
                    colorFondo = Color(0xFF956D11),
                    colorIcono = Color.White,
                    icono = Icons.Default.CardGiftcard
                )
            }

            // Distribución departamental
            item {
                DistribucionDepartamentalCards(
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            // Gráfico de Salario Base
            item {
                GraficoSalarioBase()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NominaScreenPreview() {
    NominaScreen()
}
