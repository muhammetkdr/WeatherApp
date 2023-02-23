package com.muhammetkdr.weatherapp.domain.repository

import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.domain.entity.CurrentWeatherEntity
import com.muhammetkdr.weatherapp.domain.entity.ForecastWeatherEntity
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getCurrentWeather(lat: String, long:String): Flow<Resource<CurrentWeatherEntity>>
    fun getForecastWeather(lat: String, long:String) : Flow<Resource<ForecastWeatherEntity>>
}