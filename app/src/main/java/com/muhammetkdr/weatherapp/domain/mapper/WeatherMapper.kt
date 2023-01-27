package com.muhammetkdr.weatherapp.domain.mapper

interface WeatherMapper<in I, out O> {
    fun map(input: I): O
}
