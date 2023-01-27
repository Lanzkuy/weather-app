package com.lacorp.weather_app.data.source.remote.dto

import com.squareup.moshi.Json

data class WeatherDailyDto(
    @field:Json(name = "daily")
    val weatherDailyData: WeatherDailyDataDto
)
