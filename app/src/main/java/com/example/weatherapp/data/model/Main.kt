package com.example.weatherapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Main (
    @field:Json(name = "temp") val temp: Double,
    @field:Json(name = "temp_min") val temp_min: Double,
    @field:Json(name = "temp_max") val temp_max: Double,
    @field:Json(name = "pressure") val pressure: Int,
    @field:Json(name = "humidity") val humidity: Int
)
