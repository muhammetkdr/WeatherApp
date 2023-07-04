package com.muhammetkdr.weatherapp.data.repository.city

import com.muhammetkdr.weatherapp.common.extensions.mapResource
import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.data.dto.city.CitiesResponse
import com.muhammetkdr.weatherapp.data.listmapper.ListMapper
import com.muhammetkdr.weatherapp.data.remote.city.CityRemoteDataSource
import com.muhammetkdr.weatherapp.di.Dispatcher
import com.muhammetkdr.weatherapp.di.DispatcherType
import com.muhammetkdr.weatherapp.domain.entity.cities.CitiesEntity
import com.muhammetkdr.weatherapp.domain.repository.city.CityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CityRepositoryImpl @Inject constructor(
    private val cityRemoteDataSource: CityRemoteDataSource,
    private val cityListMapper: ListMapper<CitiesResponse, CitiesEntity>,
    @Dispatcher(DispatcherType.Io) private val ioDispatcher: CoroutineContext
) : CityRepository {
    override fun getAllCities(): Flow<Resource<List<CitiesEntity>>> =
        cityRemoteDataSource.getCityResponse().map {
        it.mapResource { cityListMapper.map(this) }
    }.flowOn(ioDispatcher)
}
