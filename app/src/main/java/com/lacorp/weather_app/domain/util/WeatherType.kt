package com.lacorp.weather_app.domain.util

import androidx.annotation.DrawableRes
import com.lacorp.weather_app.R

sealed class WeatherType(
    val type: String,
    @DrawableRes val icon: Int
) {
    object Sunny : WeatherType(
        "Sunny",
        R.drawable.sunny
    )

    object Cloudy : WeatherType(
        "Cloudy",
        R.drawable.cloudy
    )

    object Rainy : WeatherType(
        "Rainy",
        R.drawable.rainy
    )

    object Stormy : WeatherType(
        "Stormy",
        R.drawable.storm
    )

    companion object {
        fun toType(code: Int): WeatherType {
            return when (code) {
                0 -> Sunny
                1 -> Cloudy
                2 -> Cloudy
                3 -> Cloudy
                45 -> Cloudy
                48 -> Cloudy
                51 -> Rainy
                53 -> Rainy
                55 -> Rainy
                56 -> Rainy
                57 -> Rainy
                61 -> Rainy
                63 -> Rainy
                65 -> Rainy
                66 -> Rainy
                67 -> Rainy
                71 -> Rainy
                73 -> Rainy
                75 -> Rainy
                77 -> Rainy
                80 -> Rainy
                81 -> Rainy
                82 -> Rainy
                85 -> Rainy
                86 -> Rainy
                95 -> Stormy
                96 -> Stormy
                99 -> Stormy
                else -> Sunny
            }
        }
    }
}