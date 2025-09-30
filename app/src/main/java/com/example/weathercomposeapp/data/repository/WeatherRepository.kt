package com.example.weathercomposeapp.data.repository

import com.example.weathercomposeapp.data.model.Weather

class WeatherRepository {

    // Dummy veri
    fun getWeatherData(): List<Weather> {
        return listOf(
            Weather("Istanbul", 25, "Güneşli"),
            Weather("Ankara", 20, "Bulutlu"),
            Weather("Izmir", 28, "Sıcak")
        )
    }
}
