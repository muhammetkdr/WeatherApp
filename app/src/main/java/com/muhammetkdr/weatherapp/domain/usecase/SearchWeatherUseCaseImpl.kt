package com.muhammetkdr.weatherapp.domain.usecase

import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.domain.entity.searchweather.SearchWeatherEntity
import com.muhammetkdr.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchWeatherUseCaseImpl @Inject constructor(private val weatherRepository: WeatherRepository) :SearchWeatherUseCase {
    override fun invoke(query:String): Flow<Resource<SearchWeatherEntity>> = weatherRepository.getSearchResponse(query)
}