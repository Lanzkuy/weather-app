package com.lacorp.weather_app.util

enum class WeatherEnum(val value: String) {
    CLOUDY("Cloudy"), SUNNY("Sunny"), RAINY("Rainy"), STORMY("Stormy");

    companion object {
        infix fun from(value: String): WeatherEnum {
            return WeatherEnum.values().firstOrNull { it.value == value }!!
        }
    }
}