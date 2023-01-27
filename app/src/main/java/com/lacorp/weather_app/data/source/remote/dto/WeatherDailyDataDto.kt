package com.lacorp.weather_app.data.source.remote.dto

import com.squareup.moshi.Json

data class WeatherDailyDataDto(
    val time: List<String>,
    @field:Json(name = "weathercode")
    val weatherCodes: List<Int>,
    @field:Json(name = "temperature_2m_max")
    val maxTemperatures: List<Double>,
    @field:Json(name = "temperature_2m_min")
    val minTemperatures: List<Double>,
)
