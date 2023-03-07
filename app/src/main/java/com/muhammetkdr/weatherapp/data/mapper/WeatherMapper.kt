package com.muhammetkdr.weatherapp.data.mapper

interface WeatherMapper<I, O> {
    fun map(input: I): O
}
