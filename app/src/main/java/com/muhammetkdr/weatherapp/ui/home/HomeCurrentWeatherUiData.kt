package com.muhammetkdr.weatherapp.ui.home

data class HomeCurrentWeatherUiData(
    val icon: String,
    val name: String,
    val country: String,
    val temperature: Double,
    val feelsLikeTemp: Double,
    val currentCondition: String
)
