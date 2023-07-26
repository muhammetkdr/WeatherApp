package com.muhammetkdr.weatherapp.data.repository.weather

import com.muhammetkdr.weatherapp.common.extensions.mapResource
import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.data.dto.current.WeatherResponse
import com.muhammetkdr.weatherapp.data.dto.forecast.ForecastResponse
import com.muhammetkdr.weatherapp.data.mapper.WeatherMapper
import com.muhammetkdr.weatherapp.data.remote.weather.WeatherRemoteDataSource
import com.muhammetkdr.weatherapp.domain.entity.currentweather.CurrentWeatherEntity
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.ForecastWeatherEntity
import com.muhammetkdr.weatherapp.domain.repository.weather.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherRemoteDataSource: WeatherRemoteDataSource,
    private val currentWeatherMapper: WeatherMapper<WeatherResponse, CurrentWeatherEntity>,
    private val forecastWeatherMapper: WeatherMapper<ForecastResponse, ForecastWeatherEntity>
) : WeatherRepository {

    override fun getCurrentWeather(lat: String, long: String): Flow<Resource<CurrentWeatherEntity>> =
        weatherRemoteDataSource.getCurrentWeather(lat, long).map {
            it.mapResource { currentWeatherMapper.map(this) }
        }

    override fun getForecastWeather(lat: String, long: String): Flow<Resource<ForecastWeatherEntity>> =
        weatherRemoteDataSource.getForecastWeather(lat, long).map {
            it.mapResource { forecastWeatherMapper.map(this) }
        }
}