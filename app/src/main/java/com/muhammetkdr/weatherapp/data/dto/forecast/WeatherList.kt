package com.muhammetkdr.weatherapp.data.dto.forecast


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.muhammetkdr.weatherapp.data.dto.current.*
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherList (
    @SerializedName("clouds")
    val clouds: Clouds?,
    @SerializedName("dt")
    val dt: Double?,
    @SerializedName("dt_txt")
    val dtTxt: String?,
    @SerializedName("main")
    val main: Main?,
    @SerializedName("pop")
    val pop: Double?,
    @SerializedName("sys")
    val sys: Sys?,
    @SerializedName("visibility")
    val visibility: Double?,
    @SerializedName("weather")
    val weather: List<Weather?>?,
    @SerializedName("wind")
    val wind: Wind?
) : Parcelable