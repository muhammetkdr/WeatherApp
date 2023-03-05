package com.muhammetkdr.weatherapp.ui.details.chart

import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.muhammetkdr.weatherapp.common.extensions.orZero
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.DatesAndTimes

class DatesAndTimesValueFormatter(var dateAndTimes: DatesAndTimes?) : ValueFormatter(){
    override fun getBarLabel(barEntry: BarEntry?): String {
        val data = dateAndTimes?.hours?.get(barEntry?.x?.toInt().orZero)
        return data.orEmpty()
    }
}