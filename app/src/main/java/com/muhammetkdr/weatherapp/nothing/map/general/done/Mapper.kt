package com.muhammetkdr.weatherapp.nothing.map.general.done

interface Mapper<in I, out O> {
    fun map(input: I): O
}