package com.muhammetkdr.weatherapp.ui.home.uidata

import com.muhammetkdr.weatherapp.domain.entity.currentweather.CurrentWeatherEntity
import com.muhammetkdr.weatherapp.domain.mapper.WeatherMapper

import javax.inject.Inject

class CurrentWeatherUiModelMapper @Inject constructor() :
    WeatherMapper<CurrentWeatherEntity, CurrentWeatherUiModel> {
    override fun map(input: CurrentWeatherEntity): CurrentWeatherUiModel {
        return CurrentWeatherUiModel(
            name = input.name,
            sys = input.sys,
            main = input.main,
            weather = input.weather
        )
    }
}