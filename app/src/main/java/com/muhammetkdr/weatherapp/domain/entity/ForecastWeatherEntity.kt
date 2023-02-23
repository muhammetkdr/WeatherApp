package com.muhammetkdr.weatherapp.domain.entity

import com.muhammetkdr.weatherapp.data.dto.current.Main
import com.muhammetkdr.weatherapp.data.dto.forecast.City
import com.muhammetkdr.weatherapp.data.dto.forecast.WeatherList

class ForecastWeatherEntity(
    val city: City,
    val main: Main,
    val list: List<WeatherList>,
    val dtTxt: String,
)