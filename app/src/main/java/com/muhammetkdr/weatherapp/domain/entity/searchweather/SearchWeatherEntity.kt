package com.muhammetkdr.weatherapp.domain.entity.searchweather

data class SearchWeatherEntity(
    val cityName : String,
    val latitude : String,
    val longitude : String,
    val country : String,
    val temp : String,
    val icon : String
)