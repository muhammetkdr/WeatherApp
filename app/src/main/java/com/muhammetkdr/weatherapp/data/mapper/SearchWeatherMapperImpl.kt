package com.muhammetkdr.weatherapp.data.mapper

import com.muhammetkdr.weatherapp.common.extensions.nullToString
import com.muhammetkdr.weatherapp.data.dto.search.SearchResponse
import com.muhammetkdr.weatherapp.domain.entity.searchweather.SearchWeatherEntity
import javax.inject.Inject

class SearchWeatherMapperImpl @Inject constructor() : WeatherMapper<SearchResponse, SearchWeatherEntity>{
    override fun map(input: SearchResponse): SearchWeatherEntity {
        return SearchWeatherEntity(
            cityName = input.name.orEmpty(),
            latitude = input.coord?.lat.nullToString(),
            longitude = input.coord?.lon.nullToString(),
            country = input.sys?.country ?: "Uganda",
            temp = input.main?.temp.nullToString(),
            icon = input.weather?.first()?.icon ?: ""
        )
    }
}