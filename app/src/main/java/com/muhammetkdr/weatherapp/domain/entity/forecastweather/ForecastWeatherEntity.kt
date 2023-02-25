package com.muhammetkdr.weatherapp.domain.entity.forecastweather

import com.muhammetkdr.weatherapp.data.dto.current.Main
import com.muhammetkdr.weatherapp.data.dto.forecast.City
import com.muhammetkdr.weatherapp.data.dto.forecast.WeatherList

data class ForecastWeatherEntity(
    val city: City,
    val main: Main,
    val list: List<WeatherList>,
    val dtTxt: String,
) {

    fun getMappedWeatherList(): MutableList<DatesAndTimes?> {
        val responseListMapper = mutableListOf<DatesAndTimes?>()
        val dates = mutableSetOf<String>()

        list.forEach {
            dates.add(it.dtTxt!!.substringBefore(" "))
        }

        dates.forEach { date ->
            val dateAndTimes: DatesAndTimes
            val hours = mutableListOf<String>()

            list.forEach { response ->

                response.dtTxt?.let {
                    if (it.contains(date)) {
                        hours.add(response.dtTxt.substringAfter(" "))
                    }
                }
            }
            dateAndTimes = DatesAndTimes(date, hours)
            responseListMapper.add(dateAndTimes)
        }
        return responseListMapper
    }

}
