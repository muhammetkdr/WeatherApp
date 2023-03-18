package com.muhammetkdr.weatherapp.domain.entity.currentweather

interface CurrentWeatherDataFormatter {
    fun getFormattedTemperature() : Double
    fun getFormattedFellsLikeTemperature() : Double
    fun getFormettedCurrentCondition() : String
}