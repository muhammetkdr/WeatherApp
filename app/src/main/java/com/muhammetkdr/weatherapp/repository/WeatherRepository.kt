package com.muhammetkdr.weatherapp.repository

import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.model.WeatherResponse

interface WeatherRepository {
    suspend fun getCurrentWeather(lat: String, long:String): Resource<WeatherResponse>
}