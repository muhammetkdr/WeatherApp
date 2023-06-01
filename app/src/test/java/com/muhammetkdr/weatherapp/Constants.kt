package com.muhammetkdr.weatherapp

import androidx.annotation.VisibleForTesting
import com.muhammetkdr.weatherapp.common.extensions.EMPTY
import com.muhammetkdr.weatherapp.data.dto.city.CitiesResponse
import com.muhammetkdr.weatherapp.data.dto.current.Clouds
import com.muhammetkdr.weatherapp.data.dto.current.Coord
import com.muhammetkdr.weatherapp.data.dto.current.Main
import com.muhammetkdr.weatherapp.data.dto.current.Sys
import com.muhammetkdr.weatherapp.data.dto.current.Weather
import com.muhammetkdr.weatherapp.data.dto.current.WeatherResponse
import com.muhammetkdr.weatherapp.data.dto.current.Wind
import com.muhammetkdr.weatherapp.data.dto.forecast.City
import com.muhammetkdr.weatherapp.data.dto.forecast.ForecastResponse
import com.muhammetkdr.weatherapp.data.dto.forecast.WeatherList
import com.muhammetkdr.weatherapp.domain.entity.cities.CitiesEntity
import com.muhammetkdr.weatherapp.domain.entity.currentweather.CurrentWeatherEntity
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.ForecastWeatherEntity
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.forecastuidata.ChildRvUiData
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.forecastuidata.DatesAndTimes
import retrofit2.Response

const val CURRENT_WEATHER_RESPONSE_FILE_NAME = "CurrentWeatherResponse.json"
const val FORECAST_WEATHER_RESPONSE_FILE_NAME = "ForecastWeatherResponse.json"
const val CITY_RESPONSE_FILE_NAME = "CityResponse.json"

const val LAT = "37.7552"
const val LON = "29.1037"

@VisibleForTesting
val testCurrentWeatherDto = WeatherResponse(
    base = null, clouds = null, cod = null, coord = null, dt = 1685538178, id = 317109, main = Main(
        24.12,
        966,
        48,
        1013,
        1013,
        24.37,
        null,
        null,
    ), name = "Denizli", sys = Sys(
        country = "TR", null, null, null, null
    ), timezone = null, visibility = null, weather = mutableListOf(
        Weather(
            description = "orta şiddetli yağmur", icon = "10d", id = null, main = "Rain"
        )
    ), wind = Wind(
        deg = 288, gust = null, speed = 1.64
    )
)

@VisibleForTesting
val mockCurrentWeatherResponse: Response<WeatherResponse> = Response.success(testCurrentWeatherDto)

@VisibleForTesting
val testForecastWeatherDto = ForecastResponse(
    city = City(
        coord = Coord(1.0, 1.0),
        country = "TR",
        id = 1.1,
        name = "Denizli",
        population = 1.1,
        sunrise = 1.1,
        sunset = 1.1,
        timezone = 1.1
    ), cnt = 40.0, cod = "200", list = listOf(
        WeatherList(
            Clouds(1),
            1.1,
            "",
            Main(1.1, 1, 1, 1, 1, 1.1, 1.1, 1.1),
            1.1,
            Sys("TR", 1, 1, 1, 1),
            1.1,
            listOf(Weather(String.EMPTY, null, 1, String.EMPTY)),
            Wind(1, 1.1, 1.1)
        )
    ), message = 0.0
)

@VisibleForTesting
val mockForecastWeatherResponse: Response<ForecastResponse> =
    Response.success(testForecastWeatherDto)

@VisibleForTesting
val testCityDto = CitiesResponse(
    name = "Denizli",
    id = 20,
    latitude = "37.7765",
    longitude = "29.0864",
    population = 993442,
    region = "Ege"
)

@VisibleForTesting
val mockCityResponse: Response<List<CitiesResponse>> = (Response.success(listOf(testCityDto)))

@VisibleForTesting
val testCityList = listOf(testCityDto)

@VisibleForTesting
val currentWeatherEntity = CurrentWeatherEntity(
    name = String.EMPTY, sys = Sys(
        country = "TR", id = 1, sunrise = 1, sunset = 1, type = 1
    ), main = Main(
        feelsLike = 1.1,
        grndLevel = 1,
        1,
        pressure = 1,
        seaLevel = 1,
        temp = 1.1,
        tempMax = 1.1,
        tempMin = 1.1
    ), weather = listOf(Weather(String.EMPTY, String.EMPTY, 1, String.EMPTY))
)

@VisibleForTesting
val forecastWeatherEntity = ForecastWeatherEntity(
    city = City(
        coord = Coord(
            1.1, 1.1
        ),
        country = "TR",
        id = 1.1,
        name = "Denizli",
        population = 1.1,
        sunrise = 1.1,
        sunset = 1.1,
        timezone = 1.1
    ), main = Main(1.1, 1, 1, 1, 1, 1.1, 1.1, 1.1), list = listOf(
        WeatherList(
            Clouds(1),
            1.1,
            "",
            Main(1.1, 1, 1, 1, 1, 1.1, 1.1, 1.1),
            1.1,
            Sys("TR", 1, 1, 1, 1),
            1.1,
            listOf(Weather(String.EMPTY, null, 1, String.EMPTY)),
            Wind(1, 1.1, 1.1)
        )
    ), dtTxt = String.EMPTY
)

@VisibleForTesting
val cityEntityList = listOf(
    CitiesEntity(
        latitude = "1",
        longitude = "1",
        cityName = "Denizli",
        cityPlateCode = "20"
    )
)

@VisibleForTesting
val testDatesAndTimesData = DatesAndTimes(
    date = "21.06.19976 Çarşamba",
    dayOfTheWeek = "Pazartesi",
    grndLevel = String.EMPTY,
    pressure = String.EMPTY,
    humidity = String.EMPTY,
    hours = listOf("12.00","15.00","18.00"),
    childRvUiData = listOf(ChildRvUiData(
        hours = "15.00",
        temperature = 24.00,
        icons = String.EMPTY
    ))
)
