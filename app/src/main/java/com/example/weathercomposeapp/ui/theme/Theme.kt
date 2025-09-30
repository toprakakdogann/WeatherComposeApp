package com.example.weathercomposeapp.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp

// Renkler
val BlueDay = Color(0xFF87CEEB)
val DarkBlueNight = Color(0xFF0D1B2A)
val CardDay = Color(0xFFBBDEFB)
val CardNight = Color(0xFF1E2A38)
val InfoCardDay = Color(0xFF90CAF9)
val InfoCardNight = Color(0xFF2C3E50)

// Typography
val WeatherTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 16.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 24.sp
    )
)

// Renk şemaları
private val LightColors = lightColorScheme(
    primary = BlueDay,
    secondary = CardDay,
    background = BlueDay,
    surface = CardDay,
)

private val DarkColors = darkColorScheme(
    primary = DarkBlueNight,
    secondary = CardNight,
    background = DarkBlueNight,
    surface = CardNight,
)

// Tema composable
@Composable
fun WeatherComposeAppTheme(
    darkTheme: Boolean = false, // true ise gece teması
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = WeatherTypography,
        content = content
    )
}
