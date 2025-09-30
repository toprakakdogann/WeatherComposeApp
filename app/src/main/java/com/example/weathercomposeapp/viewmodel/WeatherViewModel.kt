package com.example.weathercomposeapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathercomposeapp.data.RetrofitInstance
import com.example.weathercomposeapp.data.WeatherResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val _weather = MutableStateFlow<WeatherResponse?>(null)
    val weather: StateFlow<WeatherResponse?> = _weather

    private val apiKey = "8042d2791b392fd36d03a3641b48fe69" // OpenWeatherMap API key

    fun getWeather(city: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getWeather(city, apiKey)
                _weather.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
