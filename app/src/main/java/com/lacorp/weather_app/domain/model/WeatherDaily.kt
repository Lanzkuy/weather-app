package com.lacorp.weather_app.domain.model

import com.lacorp.weather_app.util.WeatherType
import java.time.LocalDate

data class WeatherDaily(
    val time: LocalDate = LocalDate.now(),
    val weatherType: WeatherType = WeatherType.Cloudy,
    val maxTemperatures: Double = 0.0,
    val minTemperatures: Double = 0.0
)
