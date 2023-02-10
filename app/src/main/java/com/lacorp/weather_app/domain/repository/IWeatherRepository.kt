package com.lacorp.weather_app.domain.repository

import com.lacorp.weather_app.domain.model.WeatherDaily
import com.lacorp.weather_app.domain.model.WeatherHourly
import com.lacorp.weather_app.common.Resource

interface IWeatherRepository {
    suspend fun getWeatherHourlyData(
        lat: Double,
        long: Double
    ): Resource<Map<Int, List<WeatherHourly>>>

    suspend fun getWeatherDailyData(
        lat: Double,
        long: Double,
        timezone: String
    ): Resource<Map<Int, List<WeatherDaily>>>
}