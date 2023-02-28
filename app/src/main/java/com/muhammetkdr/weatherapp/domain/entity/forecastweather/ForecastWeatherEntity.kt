package com.muhammetkdr.weatherapp.domain.entity.forecastweather

import com.muhammetkdr.weatherapp.common.extensions.getDateInAnotherFormat
import com.muhammetkdr.weatherapp.common.extensions.zellerCongruence
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

        list.forEach { weatherList->
            val date = weatherList.dtTxt!!.substringBefore(" ")
            dates.add(date)
        }

        dates.forEach { date ->
            val dateAndTimes: DatesAndTimes
            val hours = mutableListOf<String>()

            list.forEach { response ->

                response.dtTxt?.let {
                    if (it.contains(date)) {
                        val hourValue = response.dtTxt.substringAfter(" ")
                        val hour = hourValue.dropLast(3)
                        hours.add(hour)
                    }
                }

            }
            val formattedDate = date.getDateInAnotherFormat("yyyy-MM-dd","dd-MM-YYYY")
            val dayOfTheWeek = date.zellerCongruence()

            dateAndTimes = DatesAndTimes(formattedDate,dayOfTheWeek, hours, list)
            responseListMapper.add(dateAndTimes)
        }
        return responseListMapper
    }

}
