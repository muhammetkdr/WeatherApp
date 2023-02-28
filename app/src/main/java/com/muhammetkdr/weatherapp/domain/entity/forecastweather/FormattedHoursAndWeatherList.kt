package com.muhammetkdr.weatherapp.domain.entity.forecastweather

import com.muhammetkdr.weatherapp.data.dto.forecast.WeatherList

data class FormattedHoursAndWeatherList(val list : List<WeatherList>, val hours : List<String>)