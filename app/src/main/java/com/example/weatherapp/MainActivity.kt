package com.example.weatherapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.data.model.WeatherResponse
import com.example.weatherapp.network.ApiService
import com.example.weatherapp.network.retrofit
import com.example.weatherapp.ui.theme.WeatherAppTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

val service: ApiService = retrofit
    .create(ApiService::class.java)



private fun fetchWeatherData() {
    // Call the API
    val listCall: Call<WeatherResponse> = service.fetchWeather(
        "Taipei", BuildConfig.WEATHER_KEY
    )
    // Enqueue the call for asynchronous execution
    listCall.enqueue(object : Callback<WeatherResponse> {

        override fun onResponse(
            call: Call<WeatherResponse>,
            response: Response<WeatherResponse>
        ) {
            // Check if the response is successful
            if (response.isSuccessful) {
                val weatherResponse = response.body()
                // You can update the UI with the response here
                weatherResponse?.let {
                    Log.d("WeatherData", it.toString())
                    // Example: Show a toast with the temperature
                    //Toast.makeText(this@MainActivity, "Temp: ${it.temp}", Toast.LENGTH_LONG).show()
                }
            } else {
                // Handle the error scenario
                Log.e("API Error", response.errorBody()?.string() ?: "Unknown error")
            }
        }

        override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
            // Handle the failure scenario
            Log.e("Network Error", t.message ?: "Unknown error")
            //Toast.makeText(this@MainActivity, "Failed to fetch weather data", Toast.LENGTH_LONG).show()
        }
    })
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        fetchWeatherData()
        setContent {
            WeatherAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherAppTheme {
        Greeting("Android")
    }
}