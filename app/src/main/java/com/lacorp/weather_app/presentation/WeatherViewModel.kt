package com.lacorp.weather_app.presentation

import android.annotation.SuppressLint
import android.location.Address
import android.location.Geocoder
import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lacorp.weather_app.common.Resource
import com.lacorp.weather_app.data.mapper.toWeatherData
import com.lacorp.weather_app.domain.location.ILocationClient
import com.lacorp.weather_app.domain.model.Weather
import com.lacorp.weather_app.domain.repository.IWeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.TimeZone
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: IWeatherRepository,
    private val locationClient: ILocationClient,
) : ViewModel() {

    private val _weather: MutableStateFlow<Resource<Weather>> = MutableStateFlow(Resource.Empty)
    val weather = _weather.asStateFlow()

    private val _location: MutableStateFlow<String> = MutableStateFlow("")
    val location = _location.asStateFlow()

    private val _datetime: MutableStateFlow<String> = MutableStateFlow("")
    val datetime = _datetime.asStateFlow()

    init {
        viewModelScope.launch {
            getDatetime().collect { newDatetime ->
                _datetime.value = newDatetime
            }
        }
    }

    fun loadWeather(geocoder: Geocoder) {
        viewModelScope.launch {
            _weather.value = Resource.Loading
            locationClient.getCurrentLocation()?.let { location ->
                when (val hourly =
                    repository.getWeatherHourlyData(location.latitude, location.longitude)) {
                    is Resource.Success -> {
                        when (val daily = repository.getWeatherDailyData(
                            location.latitude,
                            location.longitude,
                            TimeZone.getDefault().id
                        )) {
                            is Resource.Success -> {
                                val lastLocation =
                                    getAddress(geocoder, location.latitude, location.longitude)

                                _location.value =
                                    lastLocation.subLocality ?: lastLocation.adminArea

                                val weather = Weather(hourly.data, daily.data)
                                _weather.value = Resource.Success(weather.toWeatherData())
                            }

                            is Resource.Error -> {
                                _weather.value = Resource.Error(daily.message)
                            }

                            else -> Unit
                        }
                    }

                    is Resource.Error -> {
                        _weather.value = Resource.Error(hourly.message)
                    }

                    else -> Unit
                }
            }
        }
    }

    @Suppress("DEPRECATION")
    private suspend fun getAddress(geocoder: Geocoder, lat: Double, lon: Double): Address {
        return suspendCoroutine {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                geocoder.getFromLocation(lat, lon, 1) { address ->
                    if (address.size != 0) {
                        it.resume(address[0])
                    }
                }
            } else {
                val address = geocoder.getFromLocation(lat, lon, 1)
                if (address != null && address.size != 0) {
                    it.resume(address[0])
                }
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDatetime(): Flow<String> = flow {
        while (true) {
            val formatter = SimpleDateFormat("EEEE , MMM yy, HH:mm a")
            val time = Calendar.getInstance().time
            val result = formatter.format(time)
                .replace("am", "AM")
                .replace("pm", "PM")
            emit(result)
            delay(10L)
        }
    }.flowOn(Dispatchers.IO)
}