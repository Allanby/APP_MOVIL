package com.example.adminrh.nomina.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SelectorDeMes(
    textoDelMes: String,
    modifier: Modifier = Modifier
) {
    // Usamos Surface para darle una apariencia de tarjeta y fondo
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium, // Bordes redondeados
        tonalElevation = 1.dp, // Una ligera sombra/elevación
        // 👇 CAMBIO AQUÍ: Añadimos el color pastel
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp), // Padding interno vertical
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center // Centramos el texto
        ) {
            Text(
                text = textoDelMes,
                style = MaterialTheme.typography.titleMedium, // Un estilo adecuado
                fontWeight = FontWeight.Bold,
                // 👇 CAMBIO AQUÍ: Color del texto que contrasta con el fondo pastel
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SelectorDeMesPreview() {
    SelectorDeMes(
        textoDelMes = "Mes en curso: Noviembre"
    )
}
