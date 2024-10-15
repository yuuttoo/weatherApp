package com.example.weatherapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherResponse(
    @field:Json(name = "cod") val code: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "weather") val weatherInfo: List<WeatherInfo>, //= emptyList()
    @field:Json(name = "main") val main: Main,
    @field:Json(name = "wind") val wind: Wind
)

