package com.example.weatherapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Main (
    @field:Json(name = "temp") val temp: Float,
    @field:Json(name = "temp_min") val temp_min: Float,
    @field:Json(name = "temp_max") val temp_max: Float,
    @field:Json(name = "pressure") val pressure: Float,
    @field:Json(name = "humidity") val humidity: Float
)
