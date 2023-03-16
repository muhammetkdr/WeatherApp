package com.muhammetkdr.weatherapp.domain.usecase

import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.domain.entity.cities.CitiesEntity
import kotlinx.coroutines.flow.Flow

interface CitiesUseCase {
    operator fun invoke(): Flow<Resource<List<CitiesEntity>>>
}