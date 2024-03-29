package com.lacorp.weather_app.data.source.remote.api

import com.lacorp.weather_app.data.source.remote.dto.WeatherDailyDto
import com.lacorp.weather_app.data.source.remote.dto.WeatherHourlyDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
        @GET("v1/forecast?hourly=weathercode,temperature_2m,windspeed_10m,pressure_msl,relativehumidity_2m")
    suspend fun getHourlyWeather(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double
    ): WeatherHourlyDto

    @GET("v1/forecast?daily=weathercode,temperature_2m_max,temperature_2m_min")
    suspend fun getDailyWeather(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double,
        @Query("timezone") timezone: String
    ): WeatherDailyDto
}