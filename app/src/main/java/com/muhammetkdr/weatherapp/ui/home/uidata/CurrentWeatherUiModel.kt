package com.muhammetkdr.weatherapp.ui.home.uidata

import com.muhammetkdr.weatherapp.data.dto.current.Main
import com.muhammetkdr.weatherapp.data.dto.current.Sys
import com.muhammetkdr.weatherapp.data.dto.current.Weather

data class CurrentWeatherUiModel (
    val name: String,
    val sys: Sys,
    val main: Main,
    val weather: List<Weather>,
)
