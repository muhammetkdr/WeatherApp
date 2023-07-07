package com.muhammetkdr.weatherapp.utils.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresApi
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.M)
class ConnectionManager @Inject constructor(@ApplicationContext private val context: Context) {

    val isConnected: Boolean
        get() = _isConnected.get()

    private val _isConnected = AtomicBoolean(false)

    init {
        listenToNetworkChanges()
    }

    private fun listenToNetworkChanges() {
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()

        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                _isConnected.set(true)
            }

            override fun onLost(network: Network) {
                _isConnected.set(false)
            }
        }

        val connectivityManager =
            context.getSystemService(ConnectivityManager::class.java) as ConnectivityManager
        connectivityManager.requestNetwork(networkRequest, networkCallback)
    }

}