package com.muhammetkdr.weatherapp.domain.usecase

import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.domain.entity.weatherlist.WeatherListEntity
import com.muhammetkdr.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherListUseCaseImpl @Inject constructor (private val weatherRepository: WeatherRepository) : WeatherListUseCase {
    override fun invoke(lat: String, long: String): Flow<Resource<List<WeatherListEntity>>> = weatherRepository.getWeatherList(lat,long)
}