package com.muhammetkdr.weatherapp.data.remote.weather

import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.data.dto.current.WeatherResponse
import com.muhammetkdr.weatherapp.data.dto.forecast.ForecastResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRemoteDataSource {
    fun getCurrentWeather(lat: String, long:String): Flow<Resource<WeatherResponse>>
    fun getForecastWeather(lat: String, long:String): Flow<Resource<ForecastResponse>>
}