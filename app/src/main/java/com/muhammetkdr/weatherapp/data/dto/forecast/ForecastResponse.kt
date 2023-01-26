package com.muhammetkdr.weatherapp.data.dto.forecast


import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @SerializedName("city")
    val city: City?,
    @SerializedName("cnt")
    val cnt: Double?,
    @SerializedName("cod")
    val cod: String?,
    @SerializedName("list")
    val list: List<WeatherList?>?,
    @SerializedName("message")
    val message: Double?
)