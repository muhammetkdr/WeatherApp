package com.muhammetkdr.weatherapp.domain.entity.forecastweather

import android.os.Parcelable
import com.muhammetkdr.weatherapp.data.dto.forecast.WeatherList
import kotlinx.parcelize.Parcelize

@Parcelize
data class DatesAndTimes(
    val date: String,
    val dayOfTheWeek: String,
    val hours: List<String>,
    val temperature: List<Double>,
    val icons: List<String>,
):Parcelable