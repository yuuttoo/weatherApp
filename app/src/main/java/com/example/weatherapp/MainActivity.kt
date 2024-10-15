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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.data.model.WeatherResponse
import com.example.weatherapp.network.ApiService
import com.example.weatherapp.network.retrofit
import com.example.weatherapp.ui.WeatherScreen
import com.example.weatherapp.ui.WeatherUiState
import com.example.weatherapp.ui.WeatherViewModel
import com.example.weatherapp.ui.theme.WeatherAppTheme


class MainActivity : ComponentActivity() {
    val vm = WeatherViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                WeatherScreen(vm)
            }
        }
    }
}



//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    WeatherAppTheme {
//        WeatherScreen()
//    }
//}