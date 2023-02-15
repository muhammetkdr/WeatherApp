package com.muhammetkdr.weatherapp.domain.listmapper

import com.muhammetkdr.weatherapp.domain.mapper.WeatherMapper

interface WeatherListMapper<I, O> : WeatherMapper<List<I>?, List<O>>