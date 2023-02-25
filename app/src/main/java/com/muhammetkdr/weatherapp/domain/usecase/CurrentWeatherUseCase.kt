package com.muhammetkdr.weatherapp.domain.usecase

import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.domain.entity.currentweather.CurrentWeatherEntity
import kotlinx.coroutines.flow.Flow

interface CurrentWeatherUseCase {
    operator fun invoke(lat: String, long:String): Flow<Resource<CurrentWeatherEntity>>
}