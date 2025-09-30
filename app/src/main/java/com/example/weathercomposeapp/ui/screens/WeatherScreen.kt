package com.example.weathercomposeapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.runtime.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.weathercomposeapp.viewmodel.WeatherViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel
) {

    // Tema durumu, başlangıçta otomatik (gündüz)
    var darkTheme by remember { mutableStateOf(false) }

    var city by remember { mutableStateOf("Istanbul") }
    val weather by viewModel.weather.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getWeather(city)
    }

    // Arka plan gradient
    val backgroundBrush = if (darkTheme) {
        Brush.verticalGradient(
            colors = listOf(Color(0xFF0D1B2A), Color(0xFF1A2C40))
        )
    } else {
        Brush.verticalGradient(
            colors = listOf(Color(0xFF87CEEB), Color(0xFFB0E0E6))
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundBrush)
            .padding(16.dp)
    ) {

        // Tema değiştirme butonu
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = { darkTheme = !darkTheme }) {
                Icon(
                    imageVector = if (darkTheme) Icons.Default.NightsStay else Icons.Default.WbSunny,
                    contentDescription = "Tema değiştir",
                    tint = if (darkTheme) Color.Yellow else Color(0xFFFFC107)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = city,
            onValueChange = { city = it },
            label = { Text("Şehir") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(
                color = if (darkTheme) Color.White else Color.Black,
                fontSize = 16.sp
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = if (darkTheme) Color.White else Color.Black,
                unfocusedTextColor = if (darkTheme) Color.Black else Color.DarkGray,
                cursorColor = if (darkTheme) Color.White else Color.Black, // cursor rengi
                focusedBorderColor = if (darkTheme) Color.White else Color(0xFF1565C0),
                unfocusedBorderColor = if (darkTheme) Color.Gray else Color.DarkGray,
                focusedLabelColor = if (darkTheme) Color.White else Color(0xFF1565C0),
                unfocusedLabelColor = if (darkTheme) Color.Gray else Color.DarkGray,
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Veriyi Getir Butonu
        Button(
            onClick = { viewModel.getWeather(city) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (darkTheme) Color(0xFF1B263B) else Color(0xFF1976D2),
                contentColor = Color.White
            )
        ) {
            Text("Veriyi Getir")
        }

        Spacer(modifier = Modifier.height(16.dp))

        weather?.let { w ->

            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (darkTheme) Color(0xFF1B263B) else Color(0xFFBBDEFB)
                )
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Column {
                        Text(
                            text = w.main.temp.toInt().toString() + "°C",
                            fontSize = 48.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (darkTheme) Color.White else Color.Black
                        )
                        Text(
                            text = translateWeatherDescription(w.weather.firstOrNull()?.description ?: "-"),
                            fontSize = 18.sp,
                            color = if (darkTheme) Color.White else Color.Black
                        )
                    }

                    val iconCode = w.weather.firstOrNull()?.icon
                    iconCode?.let { code ->
                        val iconUrl = "https://openweathermap.org/img/wn/${code}@2x.png"
                        Image(
                            painter = rememberAsyncImagePainter(iconUrl),
                            contentDescription = "Weather Icon",
                            modifier = Modifier.size(100.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                WeatherInfoCard(
                    title = "Hissedilen",
                    value = "${w.main.feels_like}°C",
                    darkTheme = darkTheme
                )
                WeatherInfoCard(
                    title = "Nem",
                    value = "${w.main.humidity}%",
                    darkTheme = darkTheme
                )
            }

        } ?: Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Veri yükleniyor...",
                fontSize = 18.sp,
                color = if (darkTheme) Color.White else Color.Black
            )
        }
    }
}

@Composable
fun WeatherInfoCard(title: String, value: String, darkTheme: Boolean) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .width(150.dp)
            .height(80.dp)
            .shadow(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (darkTheme) Color(0xFF1B263B) else Color(0xFF90CAF9)
        )
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = title, fontWeight = FontWeight.Medium, color = if (darkTheme) Color.White else Color.Black)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = value, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = if (darkTheme) Color.White else Color.Black)
        }
    }
}

fun translateWeatherDescription(description: String): String {
    return when (description.lowercase()) {
        "clear sky" -> "Açık"
        "few clouds" -> "Az Bulutlu"
        "scattered clouds" -> "Parçalı Bulutlu"
        "broken clouds" -> "Çok Bulutlu"
        "shower rain" -> "Sağanak Yağışlı"
        "rain" -> "Yağmurlu"
        "thunderstorm" -> "Fırtınalı"
        "snow" -> "Karlı"
        "mist" -> "Sisli"
        else -> description
    }
}
