package com.muhammetkdr.weatherapp.domain.mapper

import com.muhammetkdr.weatherapp.data.dto.current.WeatherResponse
import com.muhammetkdr.weatherapp.domain.entity.CurrentWeatherEntity
import com.muhammetkdr.weatherapp.nothing.map.WeatherMapper
import javax.inject.Inject

class CurrentWeatherMapperImpl @Inject constructor():
    WeatherMapper<WeatherResponse, CurrentWeatherEntity> {
    override fun map(input: WeatherResponse): CurrentWeatherEntity {
        return CurrentWeatherEntity(
            name = input.name.orEmpty(),
            sys = input.sys,
            main = input.main,
            weather = input.weather
        )
    }
}