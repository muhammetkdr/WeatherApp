package com.muhammetkdr.weatherapp.domain.entity.forecastweather

interface ForecastWeatherDataMapper {
    fun uiDataMapper(): MutableList<DatesAndTimes?>
}