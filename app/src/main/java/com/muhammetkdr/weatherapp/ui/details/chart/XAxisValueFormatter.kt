package com.muhammetkdr.weatherapp.ui.details.chart

import com.github.mikephil.charting.formatter.ValueFormatter
import com.muhammetkdr.weatherapp.common.extensions.EMPTY
import com.muhammetkdr.weatherapp.common.extensions.orZero
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.DatesAndTimes

class XAxisValueFormatter(var datesAndTimes: DatesAndTimes?) : ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        if (value.toInt() < datesAndTimes?.hours?.size.orZero) {
            val data = datesAndTimes?.hours?.get(value.toInt())
            return data.orEmpty()
        }
        return String.EMPTY
    }
}