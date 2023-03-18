package com.muhammetkdr.weatherapp.domain.entity.forecastweather.forecastuidata

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import javax.inject.Inject

@Parcelize
data class DatesAndTimes @Inject constructor(
    val date: String,
    val dayOfTheWeek: String,
    val grndLevel : String,
    val pressure : String,
    val humidity : String,
    val hours: List<String>,
    val childRvUiData: List<@RawValue ChildRvUiData>
):Parcelable