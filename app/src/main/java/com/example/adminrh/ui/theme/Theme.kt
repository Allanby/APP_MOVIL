package com.example.adminrh.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// Paleta de colores para el MODO OSCURO
private val DarkColorScheme = darkColorScheme(
    primary = BlueNavy,
    onPrimary = White,
    background = Color(0xFF1C1B1F),
    surface = Color(0xFF1C1B1F)
    // Puedes personalizar más colores aquí
)

// Paleta de colores para el MODO CLARO
private val LightColorScheme = lightColorScheme(
    primary = BlueNavy,
    onPrimary = White,
    background = White,
    surface = LightGrey
    // Puedes personalizar más colores aquí
)

// ⭐ ESTA ES LA FUNCIÓN QUE NECESITAS LLAMAR ⭐
@Composable
fun AdminrhTheme( // El nombre del tema debe empezar con mayúscula
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
    