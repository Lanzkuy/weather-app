
package com.lacorp.weather_app.domain.model

data class Weather(
    val weatherHourly: Map<Int, List<WeatherHourly>>,
    val weatherDaily: Map<Int, List<WeatherDaily>>,
    val weatherCurrent: WeatherCurrent?
)