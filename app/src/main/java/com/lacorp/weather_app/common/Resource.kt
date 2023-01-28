package com.lacorp.weather_app.common

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : Resource<T>(data)
    object Loading : Resource<Nothing>()
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}
