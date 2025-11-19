// En un nuevo archivo, por ej: com/example/adminrh/nomina/ui/DistribucionDepartamentalUI.kt

package com.example.adminrh.nomina.ui

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.adminrh.nomina.NominaViewModel
import com.example.api.models.nomina.DistribucionDepartamental

// 1. Lista de colores creativos (tonos pastel y vibrantes suaves)
private val cardColors = listOf(
    Color(0xFFB2EBF2), // Cian claro
    Color(0xFFFFF9C4), // Amarillo pálido
    Color(0xFFD1C4E9), // Lavanda
    Color(0xFFC8E6C9), // Verde menta
    Color(0xFFF8BBD0), // Rosa claro
    Color(0xFFFFE0B2), // Naranja pastel
    Color(0xFFB3E5FC), // Azul cielo
    Color(0xFFFFD180), // Naranja ambar
    Color(0xFFCFD8DC), // Gris azulado
    Color(0xFFBCAAA4)  // Marrón claro
)

@Composable
fun DistribucionDepartamentalCards(
    modifier: Modifier = Modifier,
    nominaViewModel: NominaViewModel = viewModel()
) {
    // Observamos el LiveData del ViewModel
    val distribucionList by nominaViewModel.distribucionDepartamental.observeAsState(initial = null)

    Column(modifier = modifier) {
        Text(
            "Distribución por Departamento",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
        )

        // Gestionamos el estado de carga/error/éxito
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp), // Altura fija para el contenedor del scroll
            contentAlignment = Alignment.Center
        ) {
            when {
                distribucionList == null -> {
                    // Estado de Carga
                    CircularProgressIndicator()
                }
                distribucionList!!.isEmpty() -> {
                    // Estado de Error o Vacío
                    Text("No se pudo cargar la distribución.")
                }
                else -> {
                    // Estado de Éxito: Mostramos las tarjetas
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp) // Espacio entre tarjetas
                    ) {
                        itemsIndexed(distribucionList!!) { index, item ->
                            // 3. Asignamos un color usando el índice
                            val colorIndex = index % cardColors.size
                            DepartamentoCard(
                                item = item,
                                cardColor = cardColors[colorIndex]
                            )

                        }
                    }
                }
            }
        }
    }
}

@Composable
// 4. DepartamentoCard ahora acepta un parámetro de color
fun DepartamentoCard(item: DistribucionDepartamental, cardColor: Color) {
    // Definimos un color de texto que contraste (simple blanco o negro funciona bien con pasteles)
    val textColor = if (cardColor.luminance() > 0.5) Color.Black else Color.White

    Card(
        modifier = Modifier
            .width(150.dp) // Ancho de cada tarjeta
            .fillMaxHeight(), // Ocupa toda la altura del LazyRow
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        // 5. Usamos el color recibido para el fondo de la tarjeta
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = item.departamento,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                maxLines = 2,
                minLines = 2,
                color = textColor // Asignamos el color de texto
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.distribucionPorcentualTexto,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold,
                color = textColor // Asignamos el color de texto
            )
        }
    }
}
