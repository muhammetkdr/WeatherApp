package com.muhammetkdr.weatherapp.utils.interceptors

import android.os.Build
import androidx.annotation.RequiresApi
import com.muhammetkdr.weatherapp.utils.network.ConnectionManager
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkStatusInterceptor @Inject constructor(
    private val connectionManager: ConnectionManager
) : Interceptor {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (connectionManager.isConnected) {
            chain.proceed(chain.request())
        } else {
            throw NetworkUnavailableException()
        }
    }
}

class NetworkUnavailableException(
    message: String = "Network Connection has been lost, please check your connection!"
) : IOException(message)