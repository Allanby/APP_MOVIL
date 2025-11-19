package com.example.nomina.Graficos

// --- IMPORTACIONES ---
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aay.compose.barChart.BarChart // Importación para el gráfico de barras
import com.aay.compose.barChart.model.BarParameters // Importación para el gráfico de barras
import com.example.adminrh.nomina.NominaViewModel
import com.example.api.models.nomina.SalarioBase

// --- FIN DE IMPORTACIONES ---

@Composable
fun GraficoSalarioBase(nominaViewModel: NominaViewModel = viewModel()) {
    // 1. Observar el LiveData del ViewModel de forma segura.
    val datosSalario: List<SalarioBase>? by nominaViewModel.salarioBase.observeAsState(initial = null)

    // El ViewModel se encarga de llamar a `cargarSalarioBase()` en su `init`,
    // por lo que no es necesario volver a llamarlo desde aquí con LaunchedEffect.

    Log.d("GraficoSalarioBase", "Datos recibidos en UI: ${datosSalario?.size ?: 0} items")

    // 2. Gestionar el estado de carga, error y éxito
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(450.dp) // Altura del contenedor del gráfico
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        when {
            // Estado de Carga: datosSalario es null mientras la API responde.
            datosSalario == null -> {
                CircularProgressIndicator()
            }
            // Estado Vacío: La API respondió pero no trajo datos.
            datosSalario!!.isEmpty() -> {
                Text(
                    "No hay datos de salarios para mostrar.",
                    textAlign = TextAlign.Center
                )
            }
            // Estado de Éxito: Tenemos datos para mostrar.
            else -> {
                // Pasamos los datos al nuevo gráfico de barras.
                BarChartSample(datos = datosSalario!!)
            }
        }
    }
}


@Composable
fun BarChartSample(datos: List<SalarioBase>) {

    // 1. Extraer los datos para el gráfico
    val cargos = datos.map { it.cargo }
    val salariosFemeninos = datos.map { it.salarioFemenino }
    val salariosMasculinos = datos.map { it.salarioMasculino }

    // 2. Configurar los parámetros para el gráfico de barras
    val barParameters: List<BarParameters> = listOf(
        BarParameters(
            dataName = "Salario Femenino",
            data = salariosFemeninos,
            barColor = Color(0xFFE91E63) // Color rosa para mujeres
        ),
        BarParameters(
            dataName = "Salario Masculino",
            data = salariosMasculinos,
            barColor = Color(0xFF2196F3) // Color azul para hombres
        )
    )

    // 3. Renderizar el gráfico de barras
    BarChart(
        chartParameters = barParameters,
        gridColor = Color.LightGray,
        xAxisData = cargos, // Nombres de los cargos en el eje X
        isShowGrid = true,
        animateChart = true,
        showGridWithSpacer = true,
        yAxisStyle = TextStyle(
            fontSize = 10.sp,
            color = Color.DarkGray,
        ),
        xAxisStyle = TextStyle(
            fontSize = 8.sp, // Letra pequeña para que los nombres no se solapen
            color = Color.DarkGray,
            fontWeight = FontWeight.W400,
            textAlign = TextAlign.Center
        ),
        yAxisRange = 10,
        barWidth = 40.dp // Ancho de las barras ajustado para que quepan
    )
}
