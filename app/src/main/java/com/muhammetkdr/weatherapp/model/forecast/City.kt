package com.muhammetkdr.weatherapp.model.forecast


import com.google.gson.annotations.SerializedName
import com.muhammetkdr.weatherapp.model.current.Coord

data class City(
    @SerializedName("coord")
    val coord: Coord?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("id")
    val id: Double?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("population")
    val population: Double?,
    @SerializedName("sunrise")
    val sunrise: Double?,
    @SerializedName("sunset")
    val sunset: Double?,
    @SerializedName("timezone")
    val timezone: Double?
)