package com.example.adminrh.nomina

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.MoneyOff
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.ui.graphics.Color
import com.example.adminrh.nomina.components.SelectorDeMes
import com.example.adminrh.nomina.components.TarjetaInformativa
import com.example.nomina.Graficos.GraficoDepartamental

@Composable
fun NominaScreen(nominaViewModel: NominaViewModel = viewModel()) {

    Scaffold { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Selector de mes
                    SelectorDeMes(mesActual = "Octubre 2025")

                    // Proyeccin
                    TarjetaInformativa(
                        titulo = "Proyección",
                        valor = "$150,000.00",
                        icono = Icons.Default.AttachMoney,
                        colorFondo = Color(0xDD5D025F),
                        colorIcono = Color.White,
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Deducciones
                    TarjetaInformativa(
                        titulo = "Deducciones",
                        valor = "$25,000.00",
                        icono = Icons.Default.MoneyOff,
                        colorFondo = Color(0xFF044B2A),
                        colorIcono = Color.White,
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Beneficios
                    TarjetaInformativa(
                        titulo = "Beneficios",
                        valor = "$5,000.00",
                        icono = Icons.Default.CardGiftcard,
                        colorFondo = Color(0xFF956D11),
                        colorIcono = Color.White,
                        modifier = Modifier.fillMaxWidth()
                    )

                    //Grfico
                    GraficoDepartamental()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NominaScreenPreview() {
    NominaScreen()
}
