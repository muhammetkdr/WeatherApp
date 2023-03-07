package com.muhammetkdr.weatherapp.data.listmapper

import com.muhammetkdr.weatherapp.data.mapper.WeatherMapper

interface WeatherListMapper<I, O> : WeatherMapper<List<I>?, List<O>>