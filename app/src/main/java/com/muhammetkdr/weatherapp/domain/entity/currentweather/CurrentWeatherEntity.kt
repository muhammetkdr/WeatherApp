package com.muhammetkdr.weatherapp.domain.entity.currentweather

import com.muhammetkdr.weatherapp.common.extensions.capitalizeWords
import com.muhammetkdr.weatherapp.data.dto.current.Main
import com.muhammetkdr.weatherapp.data.dto.current.Sys
import com.muhammetkdr.weatherapp.data.dto.current.Weather

data class CurrentWeatherEntity (
    val name: String,
    val sys: Sys,
    val main: Main,
    val weather: List<Weather>,
): CurrentWeatherDataFormatter{

    override fun getFormattedTemperature() : Double{
        val value = main.temp.toString()
        val hourValue = value.substringAfter('.')
        return if(hourValue.length == 2){
            val tempStr = value.dropLast(1)
            tempStr.toDouble()
        }else{
            main.temp ?: 0.0
        }
    }

    override fun getFormattedFellsLikeTemperature() : Double{
        val value = main.feelsLike.toString()
        val hourValue = value.substringAfter('.')
        return if(hourValue.length == 2){
            val tempStr = value.dropLast(1)
            tempStr.toDouble()
        }else{
            main.feelsLike ?: 0.0
        }
    }

    override fun getFormettedCurrentCondition() : String {
        return weather[0].description?.capitalizeWords() ?: "its not initialized"
    }
}