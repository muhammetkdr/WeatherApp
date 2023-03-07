package com.muhammetkdr.weatherapp.domain.repository

import com.muhammetkdr.weatherapp.common.extensions.mapResource
import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.data.dto.current.WeatherResponse
import com.muhammetkdr.weatherapp.data.dto.forecast.ForecastResponse
import com.muhammetkdr.weatherapp.data.dto.forecast.WeatherList
import com.muhammetkdr.weatherapp.data.remote.RemoteDataSource
import com.muhammetkdr.weatherapp.domain.entity.currentweather.CurrentWeatherEntity
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.ForecastWeatherEntity
import com.muhammetkdr.weatherapp.domain.entity.weatherlist.WeatherListEntity
import com.muhammetkdr.weatherapp.data.listmapper.WeatherListMapper
import com.muhammetkdr.weatherapp.data.mapper.WeatherMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val currentWeatherMapper: WeatherMapper<WeatherResponse, CurrentWeatherEntity>,
    private val forecastWeatherMapper: WeatherMapper<ForecastResponse, ForecastWeatherEntity>,
    private val weatherListMapper: WeatherListMapper<WeatherList, WeatherListEntity>
) : WeatherRepository{

    override fun getCurrentWeather(lat: String, long: String): Flow<Resource<CurrentWeatherEntity>> =
        remoteDataSource.getCurrentWeather(lat,long).map{
              it.mapResource { currentWeatherMapper.map(this)}
        }

    override fun getForecastWeather(lat: String, long: String): Flow<Resource<ForecastWeatherEntity>> =
        remoteDataSource.getForecastWeather(lat,long).map {
            it.mapResource { forecastWeatherMapper.map(this) }
    }

    override fun getWeatherList(lat: String, long: String): Flow<Resource<List<WeatherListEntity>>> =
        remoteDataSource.getWeatherList(lat,long).map {
            it.mapResource { weatherListMapper.map(this) }
        }

}