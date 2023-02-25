package com.muhammetkdr.weatherapp.domain.usecase

import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.domain.entity.currentweather.CurrentWeatherEntity
import com.muhammetkdr.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrentWeatherUseCaseImpl @Inject constructor (private val weatherRepository: WeatherRepository) :CurrentWeatherUseCase{
    override fun invoke(lat: String, long:String): Flow<Resource<CurrentWeatherEntity>> = weatherRepository.getCurrentWeather(lat,long)
}