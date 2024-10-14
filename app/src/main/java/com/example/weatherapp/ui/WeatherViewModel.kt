package com.example.weatherapp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.model.WeatherResponse
import com.example.weatherapp.network.ApiService
import com.example.weatherapp.network.retrofit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class WeatherViewModel: ViewModel() {

    //Weather Ui state
    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()


//
//    init {
//        loadInfo()
//    }

    fun loadInfo() {
        retrofit.fetchWeather("Yunlin", BuildConfig.WEATHER_KEY).enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                //success
                response.body()?.let { weatherResponse ->
                    Log.i("fetchWeather success", weatherResponse.toString())
                    for (weatherInfo in weatherResponse.weatherInfo) {
                        Log.i("WeatherResponse", "Weather ID: ${weatherInfo.id}")
                        Log.i("WeatherResponse", "Main: ${weatherInfo.main}")
                        Log.i("WeatherResponse", "Description: ${weatherInfo.description}")
                        Log.i("WeatherResponse", "Icon: ${weatherInfo.icon}")
                    }
                }

            }
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                //fail
                Log.e("fetchWeather ", "fail")
            }
        })
    }
}

//private fun fetchWeatherData() {
//    // Call the API
//    val listCall: Call<WeatherResponse> = retrofit.fetchWeather(
//        "Taipei", BuildConfig.WEATHER_KEY
//    )
//    // Enqueue the call for asynchronous execution
//    listCall.enqueue(object : Callback<WeatherResponse> {
//
//        override fun onResponse(
//            call: Call<WeatherResponse>,
//            response: Response<WeatherResponse>
//        ) {
//            // Check if the response is successful
//            if (response.isSuccessful) {
//                val weatherResponse = response.body()
//                // You can update the UI with the response here
//                weatherResponse?.let {
//                    Log.d("WeatherData", it.toString())
//                    // Example: Show a toast with the temperature
//                    //Toast.makeText(this@MainActivity, "Temp: ${it.temp}", Toast.LENGTH_LONG).show()
//                }
//            } else {
//                // Handle the error scenario
//                Log.e("API Error", response.errorBody()?.string() ?: "Unknown error")
//            }
//        }
//
//        override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
//            // Handle the failure scenario
//            Log.e("Network Error", t.message ?: "Unknown error")
//            //Toast.makeText(this@MainActivity, "Failed to fetch weather data", Toast.LENGTH_LONG).show()
//        }
//    })
//}