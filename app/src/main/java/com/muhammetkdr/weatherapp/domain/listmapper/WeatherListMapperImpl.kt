package com.muhammetkdr.weatherapp.domain.listmapper

import com.muhammetkdr.weatherapp.data.dto.forecast.WeatherList
import com.muhammetkdr.weatherapp.domain.entity.weatherlist.WeatherListEntity
import javax.inject.Inject

class WeatherListMapperImpl @Inject constructor(): WeatherListMapper<WeatherList, WeatherListEntity> {
    override fun map(input: List<WeatherList>?): List<WeatherListEntity> {
        return input?.map {
            WeatherListEntity(
                dtTxt = it.dtTxt.orEmpty()
            )
        } ?: emptyList()
    }
}