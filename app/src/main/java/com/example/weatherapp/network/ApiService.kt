package com.example.weatherapp.network

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.model.WeatherResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

interface ApiService {
    @GET("weather")
    fun fetchWeather(
        @Query("q") q: String,
        @Query("appid") appid: String,
    ): Call<WeatherResponse>
}

val retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

