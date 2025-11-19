package com.example.gerencia.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.adminrh.R

@Composable
fun TarjetaInformativa(
    titulo: String,
    cantidad: Int,
    icon: Int,
    modifier: Modifier = Modifier
) {
    var pressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (pressed) 1.05f else 1f)
    val timesBold = FontFamily(Font(R.font.timesbold))

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(12.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .clickable { pressed = !pressed }
            .clip(RoundedCornerShape(25.dp)), // Bordes redondeados
        elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF9BECE9), Color(0xFFCFC6DE))
                    )
                )
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = titulo,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = timesBold,
                    color = Color(0xFF333333)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = cantidad.toString(),
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Bold,
                    fontFamily = timesBold,
                    color = Color(0xFF111111)
                )
            }

            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(72.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TarjetaInformativaPreview() {
    TarjetaInformativa(
        titulo = "Departamentos",
        cantidad = 0,
        icon = R.drawable.ic_apartment_24
    )
}
