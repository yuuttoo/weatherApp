package com.example.weatherapp.network

import com.example.weatherapp.BuildConfig
import retrofit2.http.GET



private const val BASE_URL = "https://api.openweathermap.org/data/2.5/weather?q=Yunlin&appid=${BuildConfig.WEATHER_KEY}"

interface ApiService {

}