package com.lacorp.weather_app.data.mapper

import com.lacorp.weather_app.data.source.remote.dto.WeatherDailyDataDto
import com.lacorp.weather_app.data.source.remote.dto.WeatherHourlyDataDto
import com.lacorp.weather_app.domain.model.Weather
import com.lacorp.weather_app.domain.model.WeatherCurrent
import com.lacorp.weather_app.domain.model.WeatherDaily
import com.lacorp.weather_app.domain.model.WeatherHourly
import com.lacorp.weather_app.domain.util.WeatherType
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherData<T>(
    val index: Int,
    val data: T
)

fun WeatherHourlyDataDto.toWeatherHourly(): Map<Int, List<WeatherHourly>> {
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]

        IndexedWeatherData(
            index,
            WeatherHourly(
                LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                WeatherType.toType(weatherCode),
                temperature,
                windSpeed,
                pressure,
                humidity
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map { weather -> weather.data }
    }
}

fun WeatherDailyDataDto.toWeatherDaily(): Map<Int, List<WeatherDaily>> {
    return time.mapIndexed { index, time ->
        val weatherCode = weatherCodes[index]
        val maxTemperature = maxTemperatures[index]
        val minTemperature = minTemperatures[index]

        IndexedWeatherData(
            index,
            WeatherDaily(
                LocalDate.parse(time),
                WeatherType.toType(weatherCode),
                maxTemperature,
                minTemperature
            )
        )
    }.groupBy {
        it.index / 7
    }.mapValues {
        it.value.map { weather -> weather.data }
    }
}

fun Weather.toWeatherData(): Weather {
    val hourlyWeatherList = weatherHourly
    val dailyWeatherList = weatherDaily
    var currentWeather: WeatherCurrent? = null
    val now = LocalDateTime.now()

    val currentHourlyWeatherData = hourlyWeatherList[0]?.find {
        val hour = if (now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }

    val currentDailyWeatherData = dailyWeatherList[0]?.find {
        it.time.dayOfMonth == now.dayOfMonth
    }

    if (currentHourlyWeatherData != null && currentDailyWeatherData != null) {
        currentWeather = WeatherCurrent(
            currentHourlyWeatherData.temperature,
            currentHourlyWeatherData.weatherType,
            currentDailyWeatherData.maxTemperatures,
            currentDailyWeatherData.minTemperatures
        )
    }

    return Weather(
        hourlyWeatherList,
        dailyWeatherList,
        currentWeather
    )
}