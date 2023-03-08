package com.muhammetkdr.weatherapp.domain.entity.forecastweather

import javax.inject.Inject

data class ChildRvUiData @Inject constructor(
    val hours: String,
    val temperature: Double,
    val icons: String
) {
    fun getFormattedTemperature(): Double{
        val value = temperature.toString()
        val hourValue = value.substringAfter('.')
        return if (hourValue.length == 2) {
           val tempStr = temperature.toString().dropLast(1)
           tempStr.toDouble()
        } else {
            temperature
        }
    }
}