package com.muhammetkdr.weatherapp.domain.mapper

import com.muhammetkdr.weatherapp.data.dto.current.Main
import com.muhammetkdr.weatherapp.data.dto.current.Sys
import com.muhammetkdr.weatherapp.data.dto.current.Weather
import com.muhammetkdr.weatherapp.data.dto.current.WeatherResponse
import com.muhammetkdr.weatherapp.domain.entity.CurrentWeatherEntity
import javax.inject.Inject

class CurrentWeatherMapperImpl @Inject constructor():
    WeatherMapper<WeatherResponse, CurrentWeatherEntity> {
    override fun map(input: WeatherResponse): CurrentWeatherEntity {
        return CurrentWeatherEntity(
            name = input.name.orEmpty(),
            sys = input.sys ?: Sys("TR",1,1,1,1) ,
            main = input.main ?: Main(0.0,0,0,0,0,0.0,0.0,0.0),
            weather = input.weather.orEmpty()
        )
    }
}