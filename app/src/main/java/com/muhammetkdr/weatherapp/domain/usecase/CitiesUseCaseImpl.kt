package com.muhammetkdr.weatherapp.domain.usecase

import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.domain.entity.cities.CitiesEntity
import com.muhammetkdr.weatherapp.domain.repository.city.CityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CitiesUseCaseImpl @Inject constructor(private val cityRepository: CityRepository): CitiesUseCase {
    override fun invoke(): Flow<Resource<List<CitiesEntity>>> = cityRepository.getAllCities()
}