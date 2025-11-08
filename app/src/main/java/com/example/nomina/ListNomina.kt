package com.example.nomina

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.api.models.rh.NominaListResponce_Compo
import com.example.api.models.rh.NominaViewModel
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.adminrh.R


@Composable
fun NominasScreen(nominaViewModel: NominaViewModel = viewModel()) {
    // carga de nomina
    val nominas by nominaViewModel.nominas.collectAsState()
    LaunchedEffect(Unit) { nominaViewModel.fetchNominas() }
    val timesBold = FontFamily(Font(R.font.timesbold))

    // cantidad del selector
    var selectedCount by remember { mutableStateOf("Todos") }
    val options = listOf("5", "10", "20", "Todos")
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Text(
            text = "Nóminas Pagadas",
            fontSize = 25.sp,
            fontFamily = timesBold,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            color = Color(0xFF044395)
        )

        // Selector
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)) {

            TextField(
                readOnly = true,
                value = selectedCount,
                onValueChange = {},
                label = { Text("Cantidad de registros", fontFamily = timesBold, fontSize = 16.sp) },
                trailingIcon = {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            imageVector = if (expanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                            contentDescription = null
                        )
                    }
                },
                colors = TextFieldDefaults.colors(focusedContainerColor = Color.White),
                modifier = Modifier.fillMaxWidth()
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            selectedCount = option
                            expanded = false
                        }
                    )
                }
            }
        }

        // Lista scroll
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Filtrar según la cantidad seleccionada
            val filteredNominas = when (selectedCount) {
                "5" -> nominas.take(5)
                "10" -> nominas.take(10)
                "20" -> nominas.take(20)
                else -> nominas
            }

            items(filteredNominas) { nomina ->
                NominaCard(nomina)
            }
        }
    }
}

@Composable
fun NominaCard(nomina: NominaListResponce_Compo) {
    val timesBold = FontFamily(Font(R.font.timesbold))
    val times = FontFamily(Font(R.font.times))
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0C9078)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // logito
            Image(
                painter = painterResource(id = R.drawable.ic_nomina),
                contentDescription = "Logo Nómina",
                modifier = Modifier
                    .size(48.dp)
                    .padding(end = 16.dp)
            )

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Mes: ${nomina.mesPagado}",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontFamily = timesBold
                )
                Text(
                    text = "Fecha de pago: ${nomina.fechaPago}",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontFamily = times
                )
                Text(
                    text = "Salario bruto (Total): ${nomina.total}",
                    fontSize = 18.sp,
                    fontFamily = times,
                    color = Color.White
                )
                Text(
                    text = "Deducciones: ${nomina.deducciones}",
                    fontSize = 18.sp,
                    fontFamily = times,
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun NominasScreenPreview() {
    // simution
    val fakeNominas = listOf(
        NominaListResponce_Compo(
            mesPagado = "Enero 2025",
            fechaPago = "2025-01-31",
            total = 1500.0,
            deducciones = 200.0
        ),
        NominaListResponce_Compo(
            mesPagado = "Febrero 2025",
            fechaPago = "2025-02-28",
            total = 1600.0,
            deducciones = 180.0
        ),
        NominaListResponce_Compo(
            mesPagado = "Marzo 2025",
            fechaPago = "2025-03-31",
            total = 1550.0,
            deducciones = 190.0
        )
    )

    // viewm fake
    val fakeViewModel = object : NominaViewModel() {
        init {
            _nominas.value = fakeNominas
        }
    }

    NominasScreen(nominaViewModel = fakeViewModel)
}
