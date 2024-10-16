package com.example.weatherapp.network

import com.example.weatherapp.data.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather")
    suspend fun fetchWeatherDataByCity(@Query("q") city: String): Response<WeatherResponse>
}