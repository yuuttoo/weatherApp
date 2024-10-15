package com.example.weatherapp

import kotlin.math.roundToInt

class Helper {

    fun convertFahrenheitToCelsius(fahrenheit: Double): Int {
        // Convert Fahrenheit to Celsius
        return ((fahrenheit - 32) * 5 / 9.0).roundToInt() // Make sure to divide by 9.0 (floating point)
    }

    fun kelvinToCelsius(kelvin: Double): Int {
        return (kelvin - 273.15).toInt()
    }
}