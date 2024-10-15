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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@kotlin.OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel
) {
    val weatherUiState by viewModel.uiState.collectAsState()

        Scaffold(
            topBar = {
                TopAppBar(
                    colors = topAppBarColors(
                        containerColor = Color.Black,//MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = Color.White//MaterialTheme.colorScheme.primary,
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
                    CityInputTextField()
                    Spacer(modifier = Modifier.padding(top = 30.dp))
                    CityText()
                    Spacer(modifier = Modifier.padding(top = 40.dp))
                    WeatherIconWithDescription()
                    Spacer(modifier = Modifier.padding(top = 40.dp))
                    TempCard()
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    OtherInfoCard()
                }
            }
        }
//}

}


@Composable
fun CityInputTextField() {
    var cityName by remember {  mutableStateOf("") }
    TextField(
        value = cityName,
        onValueChange = { cityName = it},
        label = { Text("City") },
        trailingIcon = {
            IconButton(onClick = {
                // Trigger the event when the search icon is clicked
                onSearchClick(cityName)
            }) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "SearchCity"
                )
            }
        },
        maxLines = 1,
    )
}

fun onSearchClick(cityName: String) {
    Log.d("CitySearch", "Searching for city: $cityName")
}

@Composable
fun CityText()
{
    Text(
        text = "Dehli",
        fontSize = 50.sp,
        color = Color.White,
        fontFamily = FontFamily.Monospace
    )
}

@Composable
fun WeatherIconWithDescription()
{
    val image: Painter = painterResource(id = R.drawable.cloudy) // Replace with your actual image
    Column(
        modifier = Modifier
            .padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = image,
            contentDescription = "Weather Icon",
            modifier = Modifier.size(120.dp)
        )
        Text(
            text = "Mist",
            fontSize = 30.sp,
            color = Color.White,
            fontFamily = FontFamily.Monospace
        )
    }
}

@Composable
fun TempCard()
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
                text = "27°C",
                fontSize = 25.sp,
                color = Color.White,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(top = 60.dp, start = 20.dp)
            )
            //Spacer(modifier = Modifier.padding(start = 25.dp))
            Text(     //cur
                text = "29°C",
                fontSize = 35.sp,
                color = Color.White,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 20.dp)
            )
            //Spacer(modifier = Modifier.padding(start = 25.dp, end = 10.dp))
            Text(     //max
                text = "37°C",
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
fun OtherInfoCard()
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
            DashboardIconWithText()
            WindIconWithText()
            HumidityIconWithText()
        }
    }
}

@Composable
fun DashboardIconWithText()
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
            text = "1001 mb",
            fontSize = 20.sp,
            color = Color.White,
            fontFamily = FontFamily.Monospace
        )
    }

}

@Composable
fun WindIconWithText()
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
            text = "4.94 km/h",
            fontSize = 20.sp,
            color = Color.White,
            fontFamily = FontFamily.Monospace
        )
    }

}

@Composable
fun HumidityIconWithText()
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
            text = "41%",
            fontSize = 20.sp,
            color = Color.White,
            fontFamily = FontFamily.Monospace
        )
    }

}
