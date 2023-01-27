package com.muhammetkdr.weatherapp.domain.usecase

import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.domain.entity.CurrentWeatherEntity
import com.muhammetkdr.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CurrentWeatherUseCaseImpl @Inject constructor (private val weatherRepository: WeatherRepository) :CurrentWeatherUseCase{
    override fun invoke(lat: String, long:String): Flow<Resource<CurrentWeatherEntity>> = flow {
        emit(weatherRepository.getCurrentWeather(lat, long))
    }
}