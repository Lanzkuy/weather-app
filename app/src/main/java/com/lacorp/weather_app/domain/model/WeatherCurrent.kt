package com.lacorp.weather_app.domain.model

import com.lacorp.weather_app.domain.util.WeatherType

data class WeatherCurrent(
    val temperature: Double,
    val weatherType: WeatherType,
    val maxTemperatures: Double,
    val minTemperatures: Double
)
