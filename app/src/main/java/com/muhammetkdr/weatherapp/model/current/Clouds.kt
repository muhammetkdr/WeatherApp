package com.muhammetkdr.weatherapp.model.current


import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all")
    val all: Int?
)