package com.muhammetkdr.weatherapp.domain.usecase.citiesusecase

import com.muhammetkdr.weatherapp.cityEntityList
import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.domain.entity.cities.CitiesEntity
import com.muhammetkdr.weatherapp.domain.usecase.CitiesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCitiesUseCase : CitiesUseCase {

    private var showError = false

    fun updateShowError(showError: Boolean) {
        this.showError = showError
    }

    override fun invoke(): Flow<Resource<List<CitiesEntity>>> = flow {
        emit(Resource.Loading)
        if (showError){
            emit(Resource.Error("Something Bad happened.."))
        }else{
            emit(Resource.Success(cityEntityList))
        }
    }
}