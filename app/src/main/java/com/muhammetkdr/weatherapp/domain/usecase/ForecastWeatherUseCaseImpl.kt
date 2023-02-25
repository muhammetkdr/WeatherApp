package com.muhammetkdr.weatherapp.domain.usecase

import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.ForecastWeatherEntity
import com.muhammetkdr.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ForecastWeatherUseCaseImpl @Inject constructor (private val weatherRepository: WeatherRepository) : ForecastWeatherUseCase {
    override fun invoke(lat: String, long: String): Flow<Resource<ForecastWeatherEntity>> = weatherRepository.getForecastWeather(lat,long)
}