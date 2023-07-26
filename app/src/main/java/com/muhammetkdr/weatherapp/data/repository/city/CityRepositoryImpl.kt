package com.muhammetkdr.weatherapp.data.repository.city

import com.muhammetkdr.weatherapp.common.extensions.mapResource
import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.data.dto.city.CitiesResponse
import com.muhammetkdr.weatherapp.data.listmapper.ListMapper
import com.muhammetkdr.weatherapp.data.remote.city.CityRemoteDataSource
import com.muhammetkdr.weatherapp.domain.entity.cities.CitiesEntity
import com.muhammetkdr.weatherapp.domain.repository.city.CityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val cityRemoteDataSource: CityRemoteDataSource,
    private val cityListMapper: ListMapper<CitiesResponse, CitiesEntity>
) : CityRepository {
    override fun getAllCities(): Flow<Resource<List<CitiesEntity>>> =
        cityRemoteDataSource.getCityResponse().map {
        it.mapResource { cityListMapper.map(this) }
    }
}
