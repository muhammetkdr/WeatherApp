package com.muhammetkdr.weatherapp.domain.entity.forecastweather

import com.muhammetkdr.weatherapp.domain.entity.forecastweather.forecastuidata.DatesAndTimes

interface ForecastWeatherDataMapper {
    fun uiDataMapper(): List<DatesAndTimes?>
}