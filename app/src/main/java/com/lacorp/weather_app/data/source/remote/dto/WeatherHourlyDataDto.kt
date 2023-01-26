package com.lacorp.weather_app.data.source.remote.dto

import com.squareup.moshi.Json

data class WeatherHourlyDataDto(
    val time: List<String>,
    @field:Json(name = "temperature_2m")
    val temperatures: List<Double>,
    @field:Json(name = "weathercode")
    val weatherCodes: List<Int>,
    @field:Json(name = "windspeed_10m")
    val windSpeeds: List<Double>,
    @field:Json(name = "pressure_msl")
    val pressures: List<Double>,
    @field:Json(name = "relativehumid")
    val humidities: List<Double>
)

