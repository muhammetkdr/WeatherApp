package com.muhammetkdr.weatherapp.domain.entity.weatherlist

import com.muhammetkdr.weatherapp.domain.entity.forecastweather.DatesAndTimes


data class WeatherListEntity(
    val dtTxt : String
){

    val responseMapper = mutableListOf<DatesAndTimes?>()

    fun getResponse(): List<DatesAndTimes>{
        val dates = mutableListOf<String>()
        val datesFull = mutableSetOf<String>()

            val fullDates = dtTxt.map { response ->
                response.toString().substringBefore(" ")
            }.toSet()


        return emptyList()
    }

}

/*
val response = mutableListOf<String>(
    "2023-01-25 12:00:00",
    "2023-01-25 15:00:00",
    "2023-01-25 18:00:00",
    "2023-01-26 12:00:00",
    "2023-01-26 15:00:00",
    "2023-01-26 18:00:00",
    "2023-01-27 12:00:00",
    "2023-01-27 15:00:00",
    "2023-01-27 18:00:00"
)

val dates = response.map { it.substringBefore(" ") }.toSet()

val responseMapper = mutableListOf<DatesAndTimes?>()

dates.forEach { date ->
    var datesAndTimes: DatesAndTimes? = null
    val hours = mutableListOf<String>()

    response.forEach { response ->
        if (response.contains(date)) {
            hours.add(response.substringAfter(" "))
        }
    }
    datesAndTimes = DatesAndTimes(date, hours)
    responseMapper.add(datesAndTimes)
}
}*/