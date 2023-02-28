package com.muhammetkdr.weatherapp.domain.repository

import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.domain.entity.currentweather.CurrentWeatherEntity
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.ForecastWeatherEntity
import com.muhammetkdr.weatherapp.domain.entity.weatherlist.WeatherListEntity
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getCurrentWeather(lat: String, long:String): Flow<Resource<CurrentWeatherEntity>>
    fun getForecastWeather(lat: String, long:String) : Flow<Resource<ForecastWeatherEntity>>
    fun getWeatherList(lat: String, long:String): Flow<Resource<List<WeatherListEntity>>>
}