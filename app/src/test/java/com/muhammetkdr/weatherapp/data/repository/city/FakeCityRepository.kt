package com.muhammetkdr.weatherapp.data.repository.city

import com.muhammetkdr.weatherapp.cityEntityList
import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.domain.entity.cities.CitiesEntity
import com.muhammetkdr.weatherapp.domain.repository.city.CityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCityRepository : CityRepository {

    private var showError = false

    fun updateShowError(showError: Boolean) {
        this.showError = showError
    }

    override fun getAllCities(): Flow<Resource<List<CitiesEntity>>> = flow {
        emit(Resource.Loading)
        if(showError){
            emit(Resource.Error("Something went wrong"))
        }else{
            emit(Resource.Success(cityEntityList))
        }
    }

}