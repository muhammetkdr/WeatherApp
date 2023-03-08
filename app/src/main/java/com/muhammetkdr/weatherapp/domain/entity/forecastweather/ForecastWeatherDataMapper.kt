package com.muhammetkdr.weatherapp.domain.entity.forecastweather

interface ForecastWeatherDataMapper {
    fun getMappedWeatherList(): MutableList<DatesAndTimes?>
}