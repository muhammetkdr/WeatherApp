package com.muhammetkdr.weatherapp.domain.entity.forecastweather

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@Parcelize
data class ChildRvUiData @Inject constructor(
    val hours: String,
    val temperature: Double,
    val icons: String
) :Parcelable{
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