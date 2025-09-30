package com.example.weathercomposeapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weathercomposeapp.data.model.Weather

@Composable
fun WeatherCard(weather: Weather) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = weather.city)
            Text(text = "${weather.temperature}°C")
            Text(text = weather.description)
        }
    }
}
