package com.example.weathercomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.weathercomposeapp.ui.screens.WeatherScreen
import com.example.weathercomposeapp.ui.theme.WeatherComposeAppTheme
import com.example.weathercomposeapp.viewmodel.WeatherViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: WeatherViewModel by viewModels() // Lifecycle Compose dependency olmalı

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Hava durumuna göre darkTheme parametresini burada dinamik yapabilirsin
            WeatherComposeAppTheme {
                WeatherScreen(viewModel = viewModel)
            }
        }
    }
}
