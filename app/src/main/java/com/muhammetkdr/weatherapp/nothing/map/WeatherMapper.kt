package com.muhammetkdr.weatherapp.nothing.map

interface WeatherMapper<in I, out O> {
    fun map(input: I): O
}
