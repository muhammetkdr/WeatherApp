package com.muhammetkdr.weatherapp.domain.usecase

import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.domain.entity.weatherlist.WeatherListEntity
import kotlinx.coroutines.flow.Flow

interface WeatherListUseCase {
    operator fun invoke(lat: String, long:String): Flow<Resource<List<WeatherListEntity>>>
}