package com.lacorp.weather_app.domain.model

import com.lacorp.weather_app.util.WeatherType
import java.time.LocalDateTime

data class WeatherHourly(
    val time: LocalDateTime = LocalDateTime.now(),
    val weatherType: WeatherType = WeatherType.Cloudy,
    val temperature: Double = 0.0,
    val windSpeed: Double = 0.0,
    val pressure: Double = 0.0,
    val humidity: Double = 0.0
)
