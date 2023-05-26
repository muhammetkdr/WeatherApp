package com.muhammetkdr.weatherapp.ui.home

import com.muhammetkdr.weatherapp.domain.entity.forecastweather.forecastuidata.DatesAndTimes

data class HomeForecastWeatherUiData(
    val forecastWeatherList : List<DatesAndTimes>
)
