package com.muhammetkdr.weatherapp.domain.entity.cities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CitiesEntity(
    val latitude: String,
    val longitude: String,
    val cityName: String,
    val cityPlateCode: String
) : Parcelable