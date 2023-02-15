package com.muhammetkdr.weatherapp.domain.mapper

interface WeatherMapper<I, O> {
    fun map(input: I): O
}
