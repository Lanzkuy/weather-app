package com.lacorp.weather_app.common

sealed class Resource<out T>() {
    class Success<T>(data: T?) : Resource<T>()
    object Loading : Resource<Nothing>()
    class Error(message: String) : Resource<Nothing>()
}
