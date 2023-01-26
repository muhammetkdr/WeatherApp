package com.muhammetkdr.weatherapp.domain.repository

import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.data.dto.current.WeatherResponse
import com.muhammetkdr.weatherapp.data.dto.forecast.ForecastResponse
import com.muhammetkdr.weatherapp.domain.entity.CurrentWeatherEntity

interface WeatherRepository {
    suspend fun getCurrentWeather(lat: String, long:String): Resource<CurrentWeatherEntity>
    suspend fun getForecastWeather(lat: String, long:String): Resource<ForecastResponse>
}