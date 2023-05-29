package com.lacorp.weather_app.presentation

import com.lacorp.weather_app.domain.model.Weather

data class WeatherState (
    val weatherData: Weather? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)