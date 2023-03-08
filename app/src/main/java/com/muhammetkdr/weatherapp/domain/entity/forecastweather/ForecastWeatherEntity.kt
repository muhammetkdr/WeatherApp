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
) :ForecastWeatherDataMapper{

    override fun getMappedWeatherList(): MutableList<DatesAndTimes?> {
        val responseListMapper = mutableListOf<DatesAndTimes?>()
        val dates = mutableSetOf<String>()

        list.forEach { weatherList->
            val date = weatherList.dtTxt!!.substringBefore(" ")
            dates.add(date)
        }

        dates.forEach { date ->
            val dateAndTimes: DatesAndTimes
            val hours = mutableListOf<String>()
            val temperature = mutableListOf<Double>()
            val icons = mutableListOf<String>()

            val childRvUiData = mutableListOf<ChildRvUiData>()

            list.forEach { response ->

                response.dtTxt?.let {
                    if (it.contains(date)) {
                        val hourValue = response.dtTxt.substringAfter(" ")
                        val hour = hourValue.dropLast(3)
                        val temp = response.main?.temp ?: 0.0
                        val icon = response.weather?.first()?.icon.orEmpty()
                        hours.add(hour)
                        temperature.add(temp)
                        icons.add(icon)
                        childRvUiData.add(ChildRvUiData(hour,temp,icon))
                    }
                }

            }
            val formattedDate = date.getDateInAnotherFormat("yyyy-MM-dd","dd.MM.YYYY")
            val dayOfTheWeek = date.zellerCongruence()

            dateAndTimes = DatesAndTimes(
                date = formattedDate,
                dayOfTheWeek = dayOfTheWeek,
                hours = hours,
                childRvUiData = childRvUiData)
            responseListMapper.add(dateAndTimes)
        }
        return responseListMapper
    }

}
