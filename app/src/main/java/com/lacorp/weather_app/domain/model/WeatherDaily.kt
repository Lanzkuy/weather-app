package com.lacorp.weather_app.domain.model

import com.lacorp.weather_app.domain.util.WeatherType
import java.time.LocalDate

data class WeatherDaily(
    val time: LocalDate,
    val weatherType: WeatherType,
    val maxTemperatures: Double,
    val minTemperatures: Double
)
