package com.muhammetkdr.weatherapp.domain.usecase.forecastweatherusecase

import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.ForecastWeatherEntity
import com.muhammetkdr.weatherapp.domain.usecase.ForecastWeatherUseCase
import com.muhammetkdr.weatherapp.forecastWeatherEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeForecastWeatherUseCase : ForecastWeatherUseCase {

    private var showError = false

    fun updateShowError(showError: Boolean) {
        this.showError = showError
    }

    override fun invoke(lat: String, long: String): Flow<Resource<ForecastWeatherEntity>> = flow {
        emit(Resource.Loading)
        if(showError){
            emit(Resource.Error("Something bad happened.."))
        }else{
            emit(Resource.Success(forecastWeatherEntity))
        }
    }
}