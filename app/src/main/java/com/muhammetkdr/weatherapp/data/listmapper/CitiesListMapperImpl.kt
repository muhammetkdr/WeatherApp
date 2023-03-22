package com.muhammetkdr.weatherapp.data.listmapper

import com.muhammetkdr.weatherapp.data.dto.city.CitiesResponse
import com.muhammetkdr.weatherapp.domain.entity.cities.CitiesEntity
import javax.inject.Inject

class CitiesListMapperImpl @Inject constructor() : ListMapper<CitiesResponse,CitiesEntity> {
    override fun map(input: List<CitiesResponse>?): List<CitiesEntity> {
        return input?.map {
            CitiesEntity(
                latitude = it.latitude.orEmpty(),
                longitude = it.longitude.orEmpty(),
                cityPlateCode = it.id.toString(),
                cityName = it.name.orEmpty()
            )
        } ?: emptyList()
    }
}