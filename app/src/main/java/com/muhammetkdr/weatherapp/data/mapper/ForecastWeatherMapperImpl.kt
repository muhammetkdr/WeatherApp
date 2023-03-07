package com.muhammetkdr.weatherapp.data.mapper

import com.muhammetkdr.weatherapp.data.dto.current.Coord
import com.muhammetkdr.weatherapp.data.dto.current.Main
import com.muhammetkdr.weatherapp.data.dto.forecast.City
import com.muhammetkdr.weatherapp.data.dto.forecast.ForecastResponse
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.ForecastWeatherEntity
import javax.inject.Inject

class ForecastWeatherMapperImpl @Inject constructor():
    WeatherMapper<ForecastResponse, ForecastWeatherEntity> {
    override fun map(input: ForecastResponse): ForecastWeatherEntity {
        return ForecastWeatherEntity(
            city = input.city ?: City(Coord(0.0,0.0),"",0.0,"",0.0,0.0,0.0,0.0),
            main = input.list?.first()?.main ?: Main(0.0,0,0,0,0,0.0,0.0,0.0),
            list = input.list.orEmpty(),
            dtTxt = input.list?.first()?.dtTxt.orEmpty()
        )
    }
}