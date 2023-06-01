package com.muhammetkdr.weatherapp.data.repository.weather

import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.currentWeatherEntity
import com.muhammetkdr.weatherapp.domain.entity.currentweather.CurrentWeatherEntity
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.ForecastWeatherEntity
import com.muhammetkdr.weatherapp.domain.repository.weather.WeatherRepository
import com.muhammetkdr.weatherapp.forecastWeatherEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeWeatherRepository: WeatherRepository {

    private var showErrorForCurrentWeather = false
    private var showErrorForForecastWeather = false

    fun updateShowErrorForCurrentWeather(showError: Boolean) {
        this.showErrorForCurrentWeather = showError
    }

    fun updateShowErrorForForecastWeather(showError: Boolean) {
        this.showErrorForForecastWeather = showError
    }

    override fun getCurrentWeather(
        lat: String,
        long: String
    ): Flow<Resource<CurrentWeatherEntity>> = flow{
        emit(Resource.Loading)
        if(showErrorForCurrentWeather){
            emit(Resource.Error("Something bad happened..."))
        }else{
            emit(Resource.Success(currentWeatherEntity))
        }
    }

    override fun getForecastWeather(
        lat: String,
        long: String
    ): Flow<Resource<ForecastWeatherEntity>> = flow{
        emit(Resource.Loading)
        if(showErrorForForecastWeather){
            emit(Resource.Error("Something bad happened..."))
        }else{
            emit(Resource.Success(forecastWeatherEntity))
        }
    }

}