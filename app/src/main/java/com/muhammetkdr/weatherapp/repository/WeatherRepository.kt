package com.muhammetkdr.weatherapp.repository

import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.model.current.WeatherResponse
import com.muhammetkdr.weatherapp.model.forecast.ForecastResponse

interface WeatherRepository {
    suspend fun getCurrentWeather(lat: String, long:String): Resource<WeatherResponse>
    suspend fun getForecastWeather(lat: String, long:String): Resource<ForecastResponse>
}