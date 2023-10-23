package com.lacorp.weather_app.domain.location

import android.location.Location

interface ILocationClient {
    suspend fun getCurrentLocation(): Location?
    class LocationException(message: String) : Exception(message)
}