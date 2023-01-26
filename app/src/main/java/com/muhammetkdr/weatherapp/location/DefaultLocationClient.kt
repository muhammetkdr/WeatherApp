package com.muhammetkdr.weatherapp.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import com.google.android.gms.location.*
import com.muhammetkdr.weatherapp.common.extensions.hasLocationPermission
import com.muhammetkdr.weatherapp.common.utils.Const.Companion.LOCATION_REQUEST_DURATION
import com.muhammetkdr.weatherapp.common.utils.Const.Companion.LOCATION_REQUEST_MAX_DURATION
import com.muhammetkdr.weatherapp.common.utils.Const.Companion.LOCATION_REQUEST_MIN_DURATION
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultLocationClient @Inject constructor(
    @ApplicationContext private val context: Context,
    private val client: FusedLocationProviderClient,
    //private val gson: Gson
) : LocationClient {
    @SuppressLint("MissingPermission")
    override fun getLocationUpdates(interval: Long): Flow<Location> {
        return callbackFlow {
            if (!context.hasLocationPermission()) {
                throw LocationClient.LocationException(context.applicationContext.resources.getString(com.muhammetkdr.weatherapp.R.string.permission_missing))
            }
    //            gson.getAdapter()

            val locationManager =
                context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            val isNetworkEnabled =
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            if (!isGpsEnabled && !isNetworkEnabled) {
                throw LocationClient.LocationException(context.applicationContext.resources.getString(com.muhammetkdr.weatherapp.R.string.gps_disabled))
            }

            val request = LocationRequest.Builder(Priority.PRIORITY_BALANCED_POWER_ACCURACY, LOCATION_REQUEST_DURATION)
                .setWaitForAccurateLocation(false)
                .setMinUpdateIntervalMillis(LOCATION_REQUEST_MIN_DURATION)
                .setMaxUpdateDelayMillis(LOCATION_REQUEST_MAX_DURATION)
                .build()

            val locationCallback = object : LocationCallback() {
                override fun onLocationResult(result: LocationResult) {
                    super.onLocationResult(result)
                    result.locations.lastOrNull()?.let { location ->
                        launch { send(location) }
                    }
                }
            }

            client.requestLocationUpdates(
                request,
                locationCallback,
                Looper.getMainLooper()
            )

            awaitClose {
                client.removeLocationUpdates(locationCallback)
            }
        }
    }
}