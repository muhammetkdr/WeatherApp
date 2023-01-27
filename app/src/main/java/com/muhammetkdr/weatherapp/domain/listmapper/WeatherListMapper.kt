package com.muhammetkdr.weatherapp.domain.listmapper

import com.muhammetkdr.weatherapp.domain.mapper.WeatherMapper

interface WeatherListMapper<in I, out O> : WeatherMapper<List<I>?, List<O>>