package com.example.weatherapp.network

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.model.WeatherResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("weather")
    fun fetchWeather(
        @Query("q") q: String,//city name
        @Query("appid") appid: String,//api key
    ): Call<WeatherResponse>
}
//fun httpClient(loggingInterceptor: HttpLoggingInterceptor) {
//    val loggingInterceptor = HttpLoggingInterceptor.Level.BODY
//
//    OkHttpClient.Builder()
//        .addInterceptor(loggingInterceptor)
//        .build()
//}
//fun okHttpClient(): OkHttpClient {
//    val builder = OkHttpClient().newBuilder()
//        .addInterceptor(loggingInterceptor)
//    return builder.build()
//}
val retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create())
    .build()
    .create(ApiService::class.java)

