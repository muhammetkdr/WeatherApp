package com.muhammetkdr.weatherapp.domain.entity.forecastweather

import android.os.Parcelable
import com.muhammetkdr.weatherapp.data.dto.forecast.WeatherList
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import javax.inject.Inject

@Parcelize
data class DatesAndTimes @Inject constructor(
    val date: String,
    val dayOfTheWeek: String,
    val hours: List<String>,
    val childRvUiData: List<@RawValue ChildRvUiData>
):Parcelable