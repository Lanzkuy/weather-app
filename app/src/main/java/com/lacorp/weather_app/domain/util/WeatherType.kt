package com.lacorp.weather_app.domain.util

import androidx.annotation.DrawableRes
import com.lacorp.weather_app.R

sealed class WeatherType(
    val type: String,
    @DrawableRes val icon: Int
) {
    object Sunny: WeatherType(
        "Sunny",
        R.drawable.sunny
    )

    object Cloudy: WeatherType(
        "Sunny",
        R.drawable.cloudy
    )

    object Windy: WeatherType(
        "Sunny",
        R.drawable.windy
    )

    object Rainy: WeatherType(
        "Sunny",
        R.drawable.rainy
    )

    object Storm: WeatherType(
        "Sunny",
        R.drawable.storm
    )
}