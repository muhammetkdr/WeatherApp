package com.muhammetkdr.weatherapp.domain.mapping

import com.muhammetkdr.weatherapp.data.remote.RemoteDataSource
import javax.inject.Inject

class WeatherMapImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) {
}