package com.muhammetkdr.weatherapp.domain.usecase

import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.ForecastWeatherEntity
import kotlinx.coroutines.flow.Flow

interface ForecastWeatherUseCase {
    operator fun invoke(lat: String, long:String): Flow<Resource<ForecastWeatherEntity>>
}