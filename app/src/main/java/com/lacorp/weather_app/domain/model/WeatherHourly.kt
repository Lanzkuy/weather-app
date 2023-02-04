package com.lacorp.weather_app.domain.model

import com.lacorp.weather_app.domain.util.WeatherType
import java.time.LocalDateTime

data class WeatherHourly(
    val time: LocalDateTime,
    val weatherType: WeatherType,
    val temperature: Double,
    val windSpeed: Double,
    val pressure: Double,
    val humidity: Double
)
