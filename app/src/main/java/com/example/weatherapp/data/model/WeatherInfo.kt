package com.example.weatherapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class WeatherInfo (
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "main") val main: String,
    @field:Json(name = "description") val description: String,
    @field:Json(name = "icon") val icon: String
)
