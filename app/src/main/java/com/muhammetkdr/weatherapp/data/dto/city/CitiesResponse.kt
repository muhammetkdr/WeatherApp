package com.muhammetkdr.weatherapp.data.dto.city


import com.google.gson.annotations.SerializedName

data class CitiesResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("latitude")
    val latitude: String?,
    @SerializedName("longitude")
    val longitude: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("population")
    val population: Int?,
    @SerializedName("region")
    val region: String?
)