package com.muhammetkdr.weatherapp.domain.entity.currentweather

import com.muhammetkdr.weatherapp.R
import com.muhammetkdr.weatherapp.data.dto.current.Main
import com.muhammetkdr.weatherapp.data.dto.current.Sys
import com.muhammetkdr.weatherapp.data.dto.current.Weather

data class CurrentWeatherEntity (
    val name: String,
    val sys: Sys,
    val main: Main,
    val weather: List<Weather>,
){

    fun getBackground() : Int{
        return if(weather.first().main=="Clouds")
            R.drawable.cloudy
        else
            R.drawable.foggy
    }

}