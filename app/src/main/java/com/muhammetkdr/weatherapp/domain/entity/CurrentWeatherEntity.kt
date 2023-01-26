package com.muhammetkdr.weatherapp.domain.entity

import com.muhammetkdr.weatherapp.data.dto.current.Main
import com.muhammetkdr.weatherapp.data.dto.current.Sys
import com.muhammetkdr.weatherapp.data.dto.current.Weather

data class CurrentWeatherEntity (
    val name: String?,
    val sys: Sys?,
    val main: Main?,
    val weather: List<Weather?>?,
)

