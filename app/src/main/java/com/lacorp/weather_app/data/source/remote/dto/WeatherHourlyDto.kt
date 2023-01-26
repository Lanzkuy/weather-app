package com.lacorp.weather_app.data.source.remote.dto

import com.squareup.moshi.Json

data class WeatherHourlyDto(
    @field:Json(name = "hourly")
    val weatherHourlyData: WeatherHourlyDataDto
)