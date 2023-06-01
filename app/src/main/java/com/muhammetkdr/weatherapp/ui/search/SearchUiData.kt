package com.muhammetkdr.weatherapp.ui.search

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class SearchUiData(
    val latitude: String,
    val longitude: String,
    val cityName: String,
    val cityPlateCode: String
) : Parcelable