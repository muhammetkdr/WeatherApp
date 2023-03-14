package com.muhammetkdr.weatherapp.data.remote

import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.data.api.WeatherAPIService
import com.muhammetkdr.weatherapp.data.dto.current.WeatherResponse
import com.muhammetkdr.weatherapp.data.dto.forecast.ForecastResponse
import com.muhammetkdr.weatherapp.data.dto.forecast.WeatherList
import com.muhammetkdr.weatherapp.data.dto.search.SearchResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val api: WeatherAPIService,
) : RemoteDataSource {

    override fun getCurrentWeather(lat: String, long: String): Flow<Resource<WeatherResponse>> = flow {
        try {
            emit(Resource.Loading)
            val response = api.getCurrentWeather(latitude = lat, longitude = long)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Resource.Success(it))
                }
            } else {
                emit(Resource.Error(NO_DATA))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: SOMETHING_BAD_HAPPENED))
        }
    }

    override fun getForecastWeather(lat: String, long: String): Flow<Resource<ForecastResponse>> = flow {
        try {
            emit(Resource.Loading)
            val response = api.getForecastWeather(latitude = lat, longitude = long)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Resource.Success(it))
                }
            } else {
                emit(Resource.Error(NO_DATA))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: SOMETHING_BAD_HAPPENED))
        }
    }

    override fun getWeatherList(lat: String, long: String): Flow<Resource<List<WeatherList>>> = flow {
        try {
            emit(Resource.Loading)
            val response = api.getForecastWeather(latitude = lat, longitude = long)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Resource.Success(it.list!!))
                }
            } else {
                emit(Resource.Error(NO_DATA))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: SOMETHING_BAD_HAPPENED))
        }
    }

    override fun getSearchResponse(cityNameQuery: String): Flow<Resource<SearchResponse>> = flow {
        try {
            emit(Resource.Loading)
            val response = api.getSearchWeatherResponse(cityNameQuery = cityNameQuery)
            if (response.isSuccessful){
                response.body()?.let {
                    emit(Resource.Success(it))
                }
            }else {
                emit(Resource.Error(NO_DATA))
            }
        } catch (e:Exception){
            emit(Resource.Error(e.localizedMessage ?: SOMETHING_BAD_HAPPENED))
        }
    }

    companion object {
        private const val NO_DATA = "No Data!"
        private const val SOMETHING_BAD_HAPPENED = "Something bad happened.."
    }
}
