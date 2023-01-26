package com.muhammetkdr.weatherapp.data.remote

import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.data.dto.current.WeatherResponse
import com.muhammetkdr.weatherapp.data.dto.forecast.ForecastResponse

interface RemoteDataSource {
    suspend fun getCurrentWeather(lat: String, long:String): Resource<WeatherResponse>
    suspend fun getForecastWeather(lat: String, long:String): Resource<ForecastResponse>
}