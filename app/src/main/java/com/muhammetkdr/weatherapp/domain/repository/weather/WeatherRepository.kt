package com.muhammetkdr.weatherapp.domain.repository.weather

import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.domain.entity.currentweather.CurrentWeatherEntity
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.ForecastWeatherEntity
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getCurrentWeather(lat: String, long:String): Flow<Resource<CurrentWeatherEntity>>
    fun getForecastWeather(lat: String, long:String) : Flow<Resource<ForecastWeatherEntity>>
}