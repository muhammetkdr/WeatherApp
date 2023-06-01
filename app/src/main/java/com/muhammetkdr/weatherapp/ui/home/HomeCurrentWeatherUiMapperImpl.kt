package com.muhammetkdr.weatherapp.ui.home

import com.muhammetkdr.weatherapp.common.extensions.EMPTY
import com.muhammetkdr.weatherapp.data.mapper.WeatherMapper
import com.muhammetkdr.weatherapp.domain.entity.currentweather.CurrentWeatherEntity
import javax.inject.Inject

class HomeCurrentWeatherUiMapperImpl @Inject constructor() :WeatherMapper<CurrentWeatherEntity,HomeCurrentWeatherUiData>{
    override fun map(input: CurrentWeatherEntity): HomeCurrentWeatherUiData {
        return HomeCurrentWeatherUiData(
            icon = input.weather[0].icon ?: String.EMPTY,
            name = input.name,
            country = input.sys.country ?: String.EMPTY,
            temperature = input.getFormattedTemperature(),
            feelsLikeTemp = input.getFormattedFellsLikeTemperature(),
            currentCondition = input.getFormettedCurrentCondition()
        )
    }
}