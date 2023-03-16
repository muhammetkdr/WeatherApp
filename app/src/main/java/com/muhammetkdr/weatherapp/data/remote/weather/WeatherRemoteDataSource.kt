package com.muhammetkdr.weatherapp.data.remote.weather

import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.data.dto.current.WeatherResponse
import com.muhammetkdr.weatherapp.data.dto.forecast.ForecastResponse
import com.muhammetkdr.weatherapp.data.dto.forecast.WeatherList
import com.muhammetkdr.weatherapp.data.dto.search.SearchResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRemoteDataSource {
    fun getCurrentWeather(lat: String, long:String): Flow<Resource<WeatherResponse>>
    fun getForecastWeather(lat: String, long:String): Flow<Resource<ForecastResponse>>
    fun getWeatherList(lat: String, long:String): Flow<Resource<List<WeatherList>>>
    fun getSearchResponse(cityNameQuery: String): Flow<Resource<SearchResponse>>
}