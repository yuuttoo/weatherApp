package com.example.weatherapp.network

import com.example.weatherapp.ui.WeatherUiState

sealed class WeatherState {
    object Loading : WeatherState()
    data class Success(val data: WeatherUiState) : WeatherState()
    data class Error(val message: String) : WeatherState()
}