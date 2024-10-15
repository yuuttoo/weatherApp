package com.example.weatherapp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.model.WeatherResponse
import com.example.weatherapp.network.retrofit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class WeatherViewModel: ViewModel() {

    //Weather Ui state
    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    init {
        loadInfo()
    }

    private fun loadInfo() {//default city
        fetchWeatherDataByCity("Delhi")
    }

    fun fetchWeatherDataByCity(city: String) {
        // Call the API
        retrofit.fetchWeather(city, BuildConfig.WEATHER_KEY).enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                // Handle successful response
                response.body()?.let { weatherResponse ->
                    Log.i("fetchWeatherDataByCity success", weatherResponse.toString())
                    val mainWeather = weatherResponse.weatherInfo.firstOrNull()
                    val mainData = weatherResponse.main
                    val wind = weatherResponse.wind
                    _uiState.update {
                        it.copy(
                            city = weatherResponse.name,
                            main = mainWeather?.main ?: "",
                            icon = mainWeather?.icon ?: "",
                            temp = mainData.temp,
                            minTemp = mainData.temp_min,
                            maxTemp = mainData.temp_max,
                            pressure = mainData.pressure,
                            wind = wind.speed,
                            humidity = mainData.humidity,
                            userInputCity = ""//city // Update the user input city here
                        )
                    }
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e("fetchWeather ", "fail: ${t.message}")
            }
        })
    }
}




