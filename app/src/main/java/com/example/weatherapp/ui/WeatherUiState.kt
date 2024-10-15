package com.example.weatherapp.ui

import com.example.weatherapp.data.model.WeatherInfo

data class WeatherUiState(
    val city: String = "Delhi",
    val main: String = "Mist", //weather description
    val icon: String = "10n",
    val temp: Int = 29,
    val minTemp: Int = 29,
    val maxTemp: Int = 37,
    val pressure: Int = 1001,
    val wind: Double = 4.94,
    val humidity: Int = 41,
    val weatherInfo: WeatherInfo = WeatherInfo(0, "", "", ""),
    val userInputCity: String = "Delhi"//"Yunlin"
)