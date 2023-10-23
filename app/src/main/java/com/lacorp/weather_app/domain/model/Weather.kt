
package com.lacorp.weather_app.domain.model

data class Weather(
    val weatherHourly: Map<Int, List<WeatherHourly>>? = null,
    val weatherDaily: Map<Int, List<WeatherDaily>>? = null,
    val weatherCurrent: WeatherCurrent? = null
)