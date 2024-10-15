package com.example.weatherapp.ui

import com.example.weatherapp.data.model.WeatherInfo

data class WeatherUiState(
    val city: String = "",
    val main: String = "Mist", //weather description
    val icon: String = "",
    val temp: Double = 287.41,
    val minTemp: Double = 287.21,
    val maxTemp: Double = 288.33,
    val pressure: Int = 1002,
    val wind: Double = 4.94,
    val humidity: Int = 41,
    val weatherInfo: WeatherInfo = WeatherInfo(0, "", "", ""),
    val userInputCity: String = ""//"Delhi"//
)