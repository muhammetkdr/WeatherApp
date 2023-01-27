package com.muhammetkdr.weatherapp.ui.home.uidata

import com.muhammetkdr.weatherapp.domain.entity.CurrentWeatherEntity
import com.muhammetkdr.weatherapp.domain.mapper.WeatherMapper

import javax.inject.Inject

class CurrentWeatherUiModelMapper @Inject constructor() :
    WeatherMapper<CurrentWeatherEntity, CurrentWeatherUiModel> {
    override fun map(input: CurrentWeatherEntity): CurrentWeatherUiModel = with(input) {
        CurrentWeatherUiModel(
            this.name,
            this.sys,
            this.main,
            this.weather)
    }
}