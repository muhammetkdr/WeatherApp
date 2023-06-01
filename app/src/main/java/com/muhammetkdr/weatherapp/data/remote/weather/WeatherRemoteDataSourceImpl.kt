package com.muhammetkdr.weatherapp.data.remote.weather

import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.data.api.weather.WeatherAPIService
import com.muhammetkdr.weatherapp.data.dto.current.WeatherResponse
import com.muhammetkdr.weatherapp.data.dto.forecast.ForecastResponse
import com.muhammetkdr.weatherapp.di.IoDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class WeatherRemoteDataSourceImpl @Inject constructor(
    private val api: WeatherAPIService,
    @IoDispatcher private val ioDispatcher : CoroutineContext
) : WeatherRemoteDataSource {

    override fun getCurrentWeather(lat: String, long: String): Flow<Resource<WeatherResponse>> = flow {
        try {
            emit(Resource.Loading)
            val response = api.getCurrentWeather(latitude = lat, longitude = long)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Resource.Success(it))
                }  ?: emit(Resource.Error(NO_DATA))
            } else {
                emit(Resource.Error(NO_DATA))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: SOMETHING_BAD_HAPPENED))
        }
    }.flowOn(ioDispatcher)

    override fun getForecastWeather(lat: String, long: String): Flow<Resource<ForecastResponse>> = flow {
        try {
            emit(Resource.Loading)
            val response = api.getForecastWeather(latitude = lat, longitude = long)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Resource.Success(it))
                }  ?: emit(Resource.Error(NO_DATA))
            } else {
                emit(Resource.Error(NO_DATA))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: SOMETHING_BAD_HAPPENED))
        }
    }.flowOn(ioDispatcher)

    companion object {
        private const val NO_DATA = "No Data!"
        private const val SOMETHING_BAD_HAPPENED = "Something bad happened.."
    }
}
