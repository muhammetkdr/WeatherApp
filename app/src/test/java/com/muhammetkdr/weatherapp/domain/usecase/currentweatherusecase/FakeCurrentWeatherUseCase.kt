package com.muhammetkdr.weatherapp.domain.usecase.currentweatherusecase

import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.currentWeatherEntity
import com.muhammetkdr.weatherapp.domain.entity.currentweather.CurrentWeatherEntity
import com.muhammetkdr.weatherapp.domain.usecase.CurrentWeatherUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCurrentWeatherUseCase : CurrentWeatherUseCase {

    private var showError = false

    fun updateShowError(showError: Boolean) {
        this.showError = showError
    }

    override fun invoke(lat: String, long: String): Flow<Resource<CurrentWeatherEntity>> = flow {
        emit(Resource.Loading)
        if (showError){
            emit(Resource.Error("Something bad happened."))
        }else{
            emit(Resource.Success(currentWeatherEntity))
        }
    }
}