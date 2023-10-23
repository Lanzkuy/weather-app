package com.lacorp.weather_app.data.repository

import com.lacorp.weather_app.common.Resource
import com.lacorp.weather_app.data.mapper.toWeatherDaily
import com.lacorp.weather_app.data.mapper.toWeatherHourly
import com.lacorp.weather_app.data.source.remote.api.WeatherApi
import com.lacorp.weather_app.domain.model.WeatherDaily
import com.lacorp.weather_app.domain.model.WeatherHourly
import com.lacorp.weather_app.domain.repository.IWeatherRepository
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api: WeatherApi
) : IWeatherRepository {
    override suspend fun getWeatherHourlyData(
        lat: Double,
        long: Double
    ): Resource<Map<Int, List<WeatherHourly>>> {
        return try {
            Resource.Success(
                api.getHourlyWeather(
                    lat,
                    long
                ).weatherHourlyData.toWeatherHourly()
            )
        } catch (ex: Exception) {
            Resource.Error(ex.message ?: "Something went wrong")
        }
    }

    override suspend fun getWeatherDailyData(
        lat: Double,
        long: Double,
        timezone: String
    ): Resource<Map<Int, List<WeatherDaily>>> {
        return try {
            Resource.Success(
                api.getDailyWeather(
                    lat,
                    long,
                    timezone
                ).weatherDailyData.toWeatherDaily()
            )
        } catch (ex: Exception) {
            Resource.Error(ex.message ?: "Something went wrong")
        }
    }
}