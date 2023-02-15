package com.muhammetkdr.weatherapp.domain.repository

import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.data.dto.current.WeatherResponse
import com.muhammetkdr.weatherapp.data.dto.forecast.ForecastResponse
import com.muhammetkdr.weatherapp.data.remote.RemoteDataSource
import com.muhammetkdr.weatherapp.domain.entity.CurrentWeatherEntity
import com.muhammetkdr.weatherapp.domain.mapper.WeatherMapper
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class WeatherRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val weatherMapper: WeatherMapper<WeatherResponse, CurrentWeatherEntity>,
    private val ioDispatcher:CoroutineContext,
) : WeatherRepository{

    override suspend fun getCurrentWeather(lat: String, long: String): Resource<CurrentWeatherEntity> = withContext(ioDispatcher) {
        val remoteWeatherData = remoteDataSource.getCurrentWeather(lat,long)
        when(remoteWeatherData){
        is Resource.Success -> Resource.Success(weatherMapper.map(remoteWeatherData.data))
        is Resource.Error -> Resource.Error(remoteWeatherData.error)
        is Resource.Loading -> Resource.Loading
        }
    }

    override suspend fun getForecastWeather(lat: String, long: String): Resource<ForecastResponse> = withContext(ioDispatcher){
        remoteDataSource.getForecastWeather(lat,long)
    }

}