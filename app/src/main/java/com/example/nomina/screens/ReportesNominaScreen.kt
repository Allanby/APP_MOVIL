package com.example.adminrh.nomina.reportes

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
//  --- IMPORTACIONES ADICIONALES NECESARIAS ---
import com.patrykandpatrick.vico.core.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.core.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottom
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStart
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer

// ... (La función ReportesNominaScreen se queda igual) ...
@Composable
fun ReportesNominaScreen(
    modifier: Modifier = Modifier,
    viewModel: ReportesNominaViewModel = viewModel()
) {
    // Observa el estado de la UI desde el ViewModel
    val uiState by viewModel.uiState

    Scaffold { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            // Muestra diferentes vistas según el estado actual
            when (val state = uiState) {
                is ReportesNominaUiState.Loading -> {
                    CircularProgressIndicator()
                }
                is ReportesNominaUiState.Error -> {
                    Text(text = state.message, color = MaterialTheme.colorScheme.error)
                }
                is ReportesNominaUiState.Success -> {
                    GraficoNominaMensual(producer = state.producer)
                }
            }
        }
    }
}


@Composable
fun GraficoNominaMensual(
    producer: CartesianChartModelProducer,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Evolución de Salario Bruto Mensual",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))

            CartesianChartHost(
                chart = rememberCartesianChart(
                    rememberLineCartesianLayer(),
                    // 👇 --- LLAMADAS CORREGIDAS --- 👇
                    startAxis = VerticalAxis.rememberStart(),
                    bottomAxis = HorizontalAxis.rememberBottom(),
                ),
                modelProducer = producer,
                modifier = Modifier.height(300.dp)
            )
        }
    }
}

// ... (La función de Preview se queda igual) ...
@Preview(showBackground = true)
@Composable
fun ReportesNominaScreenPreview() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Vista previa de la pantalla de reportes")
    }
}

