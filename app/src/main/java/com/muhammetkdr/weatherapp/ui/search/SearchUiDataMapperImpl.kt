package com.muhammetkdr.weatherapp.ui.search

import com.muhammetkdr.weatherapp.data.listmapper.ListMapper
import com.muhammetkdr.weatherapp.domain.entity.cities.CitiesEntity
import javax.inject.Inject

class SearchUiDataMapperImpl @Inject constructor() : ListMapper<CitiesEntity, SearchUiData> {
    override fun map(input: List<CitiesEntity>?): List<SearchUiData> {
        return input?.map {
            SearchUiData(
                latitude = it.latitude,
                longitude = it.longitude,
                cityName = it.cityName,
                cityPlateCode = it.cityPlateCode
            )
        } ?: emptyList()
    }
}