package com.lacorp.weather_app.presentation

import androidx.lifecycle.ViewModel
import com.lacorp.weather_app.domain.repository.IWeatherRepository
import com.lacorp.weather_app.domain.location.ILocationClient
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: IWeatherRepository,
    private val locationClient: ILocationClient
) : ViewModel() {
}