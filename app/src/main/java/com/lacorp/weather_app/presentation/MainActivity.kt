package com.lacorp.weather_app.presentation

import android.Manifest
import android.graphics.Color
import android.location.Geocoder
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.unit.dp
import com.lacorp.weather_app.common.Resource
import com.lacorp.weather_app.presentation.component.WeatherCurrent
import com.lacorp.weather_app.presentation.component.WeatherDaily
import com.lacorp.weather_app.presentation.component.WeatherHourly
import com.lacorp.weather_app.presentation.theme.Cloudy50
import com.lacorp.weather_app.presentation.theme.CulturedWhite
import com.lacorp.weather_app.presentation.theme.WeatherAppTheme
import com.lacorp.weather_app.util.WeatherEnum
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            val geocoder = Geocoder(application, Locale.getDefault())
            viewModel.loadWeather(geocoder)
        }

        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT, Color.TRANSPARENT
            )
        )

        setContent {
            val weatherState = viewModel.weather.collectAsState().value
            val location = viewModel.location.collectAsState().value
            val datetime = viewModel.datetime.collectAsState().value

            when (weatherState) {
                is Resource.Success -> {
                    WeatherAppTheme(WeatherEnum from weatherState.data.weatherCurrent?.weatherType?.type.orEmpty()) {
                        val colorPrimaryVariant = MaterialTheme.colors.primaryVariant
                        val colorSecondaryVariant = MaterialTheme.colors.secondaryVariant

                        val gradientBackground = Brush.verticalGradient(
                            0.0f to colorPrimaryVariant,
                            1.0f to colorSecondaryVariant,
                            startY = 0.0f,
                            endY = 2500.0f
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(brush = gradientBackground)
                                .statusBarsPadding()
                                .navigationBarsPadding()
                        ) {
                            Spacer(modifier = Modifier.padding(top = 24.dp))
                            WeatherCurrent(
                                modifier = Modifier,
                                weatherCurrent = weatherState.data.weatherCurrent!!,
                                location = location,
                                datetime = datetime
                            )
                            Spacer(modifier = Modifier.padding(8.dp))
                            weatherState.data.weatherHourly!![0].let { data ->
                                LazyRow(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            top = 16.dp,
                                            start = 24.dp,
                                            end = 24.dp
                                        )
                                        .background(
                                            colorPrimaryVariant.copy(alpha = 0.25f),
                                            shape = RoundedCornerShape(12.dp)
                                        ),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                    content = {
                                        items(data!!) { hourlyData ->
                                            WeatherHourly(
                                                modifier = Modifier,
                                                painterResourceId = hourlyData.weatherType.icon,
                                                time = hourlyData.time.toLocalTime().toString(),
                                                temperature = hourlyData.temperature.toInt()
                                                    .toString(),
                                                textStyle = MaterialTheme.typography.body2,
                                                color = CulturedWhite,
                                                width = 36.dp,
                                                height = 36.dp,
                                                space = 4.dp
                                            )
                                        }
                                    }
                                )
                            }
                            Spacer(modifier = Modifier.padding(8.dp))
                            weatherState.data.weatherDaily!![0].let { data ->
                                LazyColumn(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            start = 24.dp,
                                            bottom = 24.dp,
                                            end = 24.dp
                                        )
                                        .background(
                                            colorPrimaryVariant.copy(alpha = 0.25f),
                                            shape = RoundedCornerShape(12.dp)
                                        )
                                        .weight(1f),
                                    content = {
                                        items(data!!) { dailyData ->
                                            WeatherDaily(
                                                modifier = Modifier,
                                                painterResourceId = dailyData.weatherType.icon,
                                                day = dailyData.time.dayOfWeek.name.lowercase()
                                                    .replaceFirstChar{ it .uppercase() },
                                                highTemp = dailyData.maxTemperatures.toInt()
                                                    .toString(),
                                                lowTemp = dailyData.minTemperatures.toInt()
                                                    .toString(),
                                                textStyle = MaterialTheme.typography.body1,
                                                color = CulturedWhite,
                                                width = 32.dp,
                                                height = 32.dp,
                                                space = 4.dp
                                            )
                                        }
                                    })
                            }
                        }
                    }
                }

                is Resource.Error -> {
                    Toast.makeText(
                        this@MainActivity,
                        weatherState.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is Resource.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(CulturedWhite),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Cloudy50)
                    }
                }

                else -> {}
            }
        }
    }
}