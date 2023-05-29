package com.lacorp.weather_app.data.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.lacorp.weather_app.domain.location.ILocationClient
import com.lacorp.weather_app.util.hasLocationPermission
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class LocationClient @Inject constructor(
    private val context: Context,
    private val locationProviderClient: FusedLocationProviderClient
) : ILocationClient {

    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): Location? {
        if (!context.hasLocationPermission()) {
            throw ILocationClient.LocationException("Missing location permission")
        }

        val locationManager =
            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        if (!isGpsEnabled && !isNetworkEnabled) {
            throw ILocationClient.LocationException("GPS is disabled")
        }

        return suspendCancellableCoroutine { suspend ->
            locationProviderClient.lastLocation.apply {
                if (isComplete) {
                    if (isSuccessful) {
                        suspend.resume(result)
                    } else {
                        suspend.resume(null)
                    }
                    return@suspendCancellableCoroutine
                }
                addOnSuccessListener {
                    suspend.resume(it)
                }
                addOnFailureListener {
                    suspend.resume(null)
                }
                addOnCanceledListener {
                    suspend.cancel()
                }
            }
        }
    }
}