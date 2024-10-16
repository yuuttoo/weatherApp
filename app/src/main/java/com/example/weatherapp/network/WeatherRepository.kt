package com.example.weatherapp.network

import com.example.weatherapp.data.model.WeatherResponse
import retrofit2.Response

class WeatherRepository(private val weatherService: WeatherService) {
    suspend fun fetchWeatherDataByCity(city: String): Response<WeatherResponse> {
        return weatherService.fetchWeatherDataByCity(city)
    }
}
