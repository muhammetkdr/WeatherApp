package com.muhammetkdr.weatherapp.ui.home

import com.muhammetkdr.weatherapp.data.mapper.WeatherMapper
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.ForecastWeatherEntity
import javax.inject.Inject

class HomeForecastWeatherUiMapperImpl @Inject constructor() : WeatherMapper<ForecastWeatherEntity, HomeForecastWeatherUiData> {
    override fun map(input: ForecastWeatherEntity): HomeForecastWeatherUiData {
        return HomeForecastWeatherUiData(
            forecastWeatherList = input.uiDataMapper()
        )
    }
}