package com.muhammetkdr.weatherapp.model.forecast


import com.google.gson.annotations.SerializedName

data class Main(
    @SerializedName("feels_like")
    val feelsLike: Double?,
    @SerializedName("grnd_level")
    val grndLevel: Double?,
    @SerializedName("humidity")
    val humidity: Double?,
    @SerializedName("pressure")
    val pressure: Double?,
    @SerializedName("sea_level")
    val seaLevel: Double?,
    @SerializedName("temp")
    val temp: Double?,
    @SerializedName("temp_kf")
    val tempKf: Double?,
    @SerializedName("temp_max")
    val tempMax: Double?,
    @SerializedName("temp_min")
    val tempMin: Double?
)