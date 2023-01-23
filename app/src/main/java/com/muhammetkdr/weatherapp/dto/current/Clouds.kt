package com.muhammetkdr.weatherapp.dto.current


import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all")
    val all: Int?
)