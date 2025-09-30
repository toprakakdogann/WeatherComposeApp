package com.example.weathercomposeapp.data

data class WeatherResponse(
    val main: Main,
    val weather: List<Weather>
)

data class Main(
    val temp: Float,
    val feels_like: Float,
    val humidity: Int
)

data class Weather(
    val description: String,
    val icon: String
)
