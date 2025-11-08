package com.example.adminrh.nomina.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adminrh.R
@Composable
fun SelectorDeMes(
    mesActual: String,
    modifier: Modifier = Modifier
) {
    // fuente
    val timesBold = FontFamily(Font(R.font.timesbold))


    var activo by remember { mutableStateOf(false) }
    val fondoAnimado by animateColorAsState(
        targetValue = if (activo) Color(0xFF004AAD) else Color(0xFF0059D6),
        animationSpec = tween(durationMillis = 700),
        label = "animColor"
    )

    // cntenedor principal
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(70.dp)
            .shadow(10.dp, shape = RoundedCornerShape(16.dp))
            .background(fondoAnimado, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .then(Modifier),
        color = Color.Transparent,
        shape = RoundedCornerShape(16.dp),
        onClick = { activo = !activo }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(fondoAnimado, shape = RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = mesActual,
                style = TextStyle(
                    fontFamily = timesBold,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    letterSpacing = 1.sp
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SelectorDeMesPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3F4F6)),
        contentAlignment = Alignment.Center
    ) {
        SelectorDeMes(mesActual = "Octubre 2025")
    }
}
