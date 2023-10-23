package com.lacorp.weather_app.domain.model

import com.lacorp.weather_app.util.WeatherType

data class WeatherCurrent(
    val temperature: Double = 0.0,
    val weatherType: WeatherType = WeatherType.Cloudy,
    val maxTemperatures: Double = 0.0,
    val minTemperatures: Double = 0.0
)
