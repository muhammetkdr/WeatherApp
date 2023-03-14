package com.muhammetkdr.weatherapp.domain.usecase

import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.domain.entity.searchweather.SearchWeatherEntity
import kotlinx.coroutines.flow.Flow

interface SearchWeatherUseCase {
    operator fun invoke(query : String): Flow<Resource<SearchWeatherEntity>>
}