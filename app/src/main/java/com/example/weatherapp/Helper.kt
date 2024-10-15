package com.example.weatherapp

class Helper {

    fun convertFahrenheitToCelsius(fahrenheit: Double): Double {
        // Convert Fahrenheit to Celsius
        return (fahrenheit - 32) * 5 / 9
    }
}