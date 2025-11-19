package com.example.adminrh.nomina.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adminrh.R

@Composable
fun TarjetaInformativa(
    titulo: String,
    valor: String,
    modifier: Modifier = Modifier,
    icono: ImageVector = Icons.Default.AttachMoney,
    colorFondo: Color = Color(0xDD5D025F),
    colorIcono: Color = Color.White,
    colorTexto: Color = Color.White
) {
    val timesBold = FontFamily(Font(R.font.timesbold))

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(110.dp)
            .shadow(8.dp, shape = RoundedCornerShape(50.dp)),
        shape = RoundedCornerShape(50.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorFondo
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 2.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            Text(
                text = titulo,
                style = TextStyle(
                    fontFamily = timesBold,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    // 👇 USA EL NUEVO PARÁMETRO AQUÍ 👇
                    color = colorTexto



                )
            )

            Spacer(modifier = Modifier.height(6.dp))

            //icono money
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = icono,
                    contentDescription = "Icono",
                    tint = colorIcono,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(14.dp))
                Text(
                    text = valor,
                    style = TextStyle(
                        fontFamily = timesBold,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        color = colorTexto
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TarjetaInformativaPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFEFEF))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        TarjetaInformativa(
            titulo = "Proyección",
            valor = "4,502,895.98",
        )
    }
}
