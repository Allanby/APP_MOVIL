package com.example.adminrh.nomina

// Se eliminó la importación incorrecta de 'android.icu.text.NumberFormat'. Se usará la de Java.
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.TrendingUp
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
// Se eliminó la importación incorrecta de 'androidx.wear.compose.foundation.weight'

// Importaciones correctas de tus componentes y ViewModel
import com.example.adminrh.nomina.components.SelectorDeMes
import com.example.adminrh.nomina.components.TarjetaInformativa
import com.example.adminrh.nomina.ui.DistribucionDepartamentalCards
//import com.example.nomina.Graficos.BarChartSample
import com.example.nomina.Graficos.GraficoSalarioBase
import java.text.NumberFormat // Usar la implementación estándar de Java
import java.util.Locale

@Composable
fun NominaScreen(nominaViewModel: NominaViewModel = viewModel()) {
    // Observamos todos los estados desde el ViewModel
    val totalBeneficio by nominaViewModel.totalBeneficio.observeAsState(initial = null)
    val totalDeduccion by nominaViewModel.totalDeduccion.observeAsState(initial = null)
    val nominaMensual by nominaViewModel.nominaMensual.observeAsState(initial = emptyList())
    val proyeccion by nominaViewModel.proyeccion.observeAsState(initial = null)
    val salarioBase by nominaViewModel.salarioBase.observeAsState(initial = emptyList())


    // Formateador de moneda
    val currencyFormatter = remember { NumberFormat.getCurrencyInstance(Locale("es", "DO")) }

    Scaffold { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp) // Espacio uniforme entre elementos
        ) {
            // 1. Selector de Mes
            item {
                SelectorDeMes(
                    textoDelMes = "Mes en curso: Noviembre"
                    // Ya no se necesitan los parámetros onMesSiguiente y onMesAnterior
                )

            }

            // 2. Fila de Tarjetas Superiores (Proyección y Deducciones)
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Tarjeta de Proyección (dorado pastel)
                    TarjetaInformativa(
                        titulo = "Proyección",
                        valor = proyeccion?.let { currencyFormatter.format(it) } ?: "Cargando...",
                        modifier = Modifier.weight(1f),
                        colorFondo = Color(0xFFFAE0B4),
                        colorIcono = Color(0xFF8C5A02), // Contraste oscuro
                        colorTexto = Color(0xFF8C5A02), // Contraste oscuro
                        icono = Icons.Default.TrendingUp
                    )

                    // Tarjeta de Deducciones (azul pastel)
                    TarjetaInformativa(
                        titulo = "Deducciones",
                        valor = totalDeduccion?.let { currencyFormatter.format(it) } ?: "Cargando...",
                        modifier = Modifier.weight(1f),
                        // Color de fondo azul marino
                        colorFondo = Color(0xFF0A2A66),
                        // Color de icono y texto blanco para contraste
                        colorIcono = Color.White,
                        colorTexto = Color.White,
                        icono = Icons.Default.ArrowDownward
                    )
                }
            }

            // 3. Tarjeta Inferior (Beneficios)
            item {
                TarjetaInformativa(
                    titulo = "Beneficios",
                    valor = totalBeneficio?.let { currencyFormatter.format(it) } ?: "Cargando...",
                    modifier = Modifier.fillMaxWidth(), // Esta ocupa todo el ancho
                    colorFondo = Color(0xFFFAB4B4), // Rojo poco intenso / pastel
                    colorIcono = Color(0xFF8C0202), // Rojo oscuro para contraste
                    colorTexto = Color(0xFF8C0202), // Rojo oscuro para contraste
                    icono = Icons.Default.ArrowUpward // Icono apropiado para beneficio
                )

    }

            item {
                DistribucionDepartamentalCards(
                    // El modifier aquí es opcional, pero podrías usarlo para añadir más padding si fuera necesario
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            // 4. Gráfico de Nómina Mensual
//            item {
//                if (nominaMensual.isEmpty()) {
//                   // GraficoNominaMensual(datosNomina = nominaMensual)
//                } else {
//                    // Muestra un indicador de carga si los datos del gráfico aún no han llegado
//                    Box(modifier = Modifier.fillMaxWidth().height(250.dp), contentAlignment = Alignment.Center) {
//                        CircularProgressIndicator()
//                    }
//                }
//            }
            item {
                //if (salarioBase.isEmpty()) {
                // Aquí podrías añadir un Text() con un título si quieres
                // Text("Salario Base por Cargo y Género", style = MaterialTheme.typography.titleMedium)
                // Spacer(modifier = Modifier.height(8.dp))
                GraficoSalarioBase()                    //BarChartSample()
                // } else {
                // Opcional: mostrar un indicador de carga si los datos aún no llegan
                // Box(modifier = Modifier.fillMaxWidth().height(300.dp), contentAlignment = Alignment.Center) {
                //    CircularProgressIndicator()
                // }
                //}
            }
        }
    }
}

// Preview para ver el diseño sin ejecutar la app
@Preview(showBackground = true)
@Composable
fun NominaScreenPreview() {
    NominaScreen()
}
