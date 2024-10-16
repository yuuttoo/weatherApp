package com.example.weatherapp.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapp.Helper
import com.example.weatherapp.R
import com.example.weatherapp.data.model.WeatherState


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel
) {
    val weatherState by viewModel.weatherState.collectAsState()
    val weatherUiState by viewModel.uiState.collectAsState()

        Scaffold(
            topBar = {
                TopAppBar(
                    colors = topAppBarColors(
                        containerColor = Color.Black,
                        titleContentColor = Color.White
                    ),
                    title = {
                        Text("Weather")
                    }
                )
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .background(Color.DarkGray)
                    .fillMaxSize()
                    .padding(innerPadding), // Add innerPadding here to place it under the top bar
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(top = 20.dp)
                ) {
                    when (weatherState) {
                        is WeatherState.Loading -> {
                            CircularProgressIndicator(modifier = Modifier
                                .align(Alignment.CenterHorizontally))
                        }
                        is WeatherState.Success -> {
                            CityInputTextField(
                                cityName = weatherUiState.userInputCity,
                                onSearchClick = { cityName ->
                                    viewModel.fetchWeatherDataByCity(cityName)
                                    Log.i("cityName", cityName)
                                }
                            )
                            Spacer(modifier = Modifier.padding(top = 30.dp))
                            CityText(weatherUiState.city)
                            Spacer(modifier = Modifier.padding(top = 40.dp))
                            WeatherIconWithDescription(weatherUiState.icon, weatherUiState.main)
                            Spacer(modifier = Modifier.padding(top = 40.dp))
                            TempCard(Helper().kelvinToCelsius(weatherUiState.minTemp), Helper().kelvinToCelsius(weatherUiState.temp), Helper().kelvinToCelsius(weatherUiState.maxTemp))
                            Spacer(modifier = Modifier.padding(top = 10.dp))
                            OtherInfoCard(weatherUiState.pressure, weatherUiState.wind, weatherUiState.humidity)
                        }
                        is WeatherState.Error -> {
                            Text(
                                text = (weatherState as WeatherState.Error).message,
                                color = Color.Red,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                    }

                }
            }
        }
//}

}


@Composable
fun CityInputTextField(
    cityName: String,
    onSearchClick: (String) -> Unit
    ) {
    var text by remember {  mutableStateOf(cityName) }
    TextField(
        value = text,
        onValueChange = { text = it},
        label = { Text("City") },
        trailingIcon = {
            IconButton(onClick = {
                // Trigger the event when the search icon is clicked
                onSearchClick(text)
            }) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "SearchCity"
                )
            }
        },
        maxLines = 1,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchClick(text)
            }
        )
    )
}

@Composable
fun CityText(cityName: String)
{
    Text(
        text = cityName,
        fontSize = 40.sp,
        color = Color.White,
        fontFamily = FontFamily.Monospace
    )
}

@Composable
fun WeatherIconWithDescription(
    icon: String,
    desc: String
)
{
    val image: Painter = painterResource(id = R.drawable.cloudy)
    val iconUrl = "https://openweathermap.org/img/wn/$icon@2x.png"
    Log.i("icon", iconUrl)

    Column(
        modifier = Modifier
            .padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = iconUrl,
            contentDescription = "Weather Icon",
            placeholder = image,
            modifier = Modifier.size(120.dp),
        )
        Text(
            text = desc,
            fontSize = 30.sp,
            color = Color.White,
            fontFamily = FontFamily.Monospace
        )
    }
}

@Composable
fun TempCard(
    minTemp: Int,
    curTemp: Int,
    maxTemp: Int
)
{
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Gray,
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
            .border(
                border = BorderStroke(3.dp, Color.Cyan)
            )

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(//min
                text = "$minTemp°C",
                fontSize = 25.sp,
                color = Color.White,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(top = 60.dp, start = 20.dp)
            )
            Text(     //cur
                text = "$curTemp°C",
                fontSize = 35.sp,
                color = Color.White,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 20.dp)
            )
            Text(     //max
                text = "$maxTemp°C",
                fontSize = 25.sp,
                color = Color.White,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .padding(top = 60.dp, end = 20.dp)
            )
        }
    }
}

@Composable
fun OtherInfoCard(
    pressure: Int,
    wind: Double,
    humidity: Int
)
{
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.DarkGray,
        ),
        modifier = Modifier
            .height(180.dp)
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DashboardIconWithText(pressure)
            WindIconWithText(wind)
            HumidityIconWithText(humidity)
        }
    }
}

@Composable
fun DashboardIconWithText(
    pressure: Int
)
{
    val image: Painter = painterResource(id = R.drawable.ic_dashboard)

    Column(
        modifier = Modifier
            .padding(top = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = image,
            contentDescription = "Dashboard Icon",
            modifier = Modifier.size(80.dp)
        )
        Text(
            text = "PRESSURE",
            fontSize = 20.sp,
            color = Color.White,
            fontFamily = FontFamily.Monospace
        )
        Text(
            text = "$pressure mb",
            fontSize = 20.sp,
            color = Color.White,
            fontFamily = FontFamily.Monospace
        )
    }

}

@Composable
fun WindIconWithText(
    wind: Double
)
{
    val image: Painter = painterResource(id = R.drawable.ic_wind)

    Column(
        modifier = Modifier
            .padding(top = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = image,
            contentDescription = "Wind Icon",
            modifier = Modifier.size(80.dp)
        )
        Text(
            text = "Wind",
            fontSize = 20.sp,
            color = Color.White,
            fontFamily = FontFamily.Monospace
        )
        Text(
            text = "$wind km/h",
            fontSize = 20.sp,
            color = Color.White,
            fontFamily = FontFamily.Monospace
        )
    }

}

@Composable
fun HumidityIconWithText(
    humidity: Int
)
{

    val image: Painter = painterResource(id = R.drawable.ic_humidity) // Replace with your actual image

    Column(
        modifier = Modifier
            .padding(top = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = image,
            contentDescription = "Humidity Icon",
            modifier = Modifier.size(80.dp)
        )
        Text(
            text = "Humidity",
            fontSize = 20.sp,
            color = Color.White,
            fontFamily = FontFamily.Monospace
        )
        Text(
            text = "$humidity%",
            fontSize = 20.sp,
            color = Color.White,
            fontFamily = FontFamily.Monospace
        )
    }

}
