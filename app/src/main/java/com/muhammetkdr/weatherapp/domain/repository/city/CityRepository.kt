package com.muhammetkdr.weatherapp.domain.repository.city

import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.domain.entity.cities.CitiesEntity
import kotlinx.coroutines.flow.Flow

interface CityRepository {
    fun getAllCities() : Flow<Resource<List<CitiesEntity>>>
}