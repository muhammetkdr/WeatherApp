package com.muhammetkdr.weatherapp.data.remote

import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.data.WeatherAPIService
import com.muhammetkdr.weatherapp.data.dto.current.WeatherResponse
import com.muhammetkdr.weatherapp.data.dto.forecast.ForecastResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class RemoteDataSourceImpl @Inject constructor(
    private val api: WeatherAPIService,
    private val ioDispatcher:CoroutineContext
) : RemoteDataSource {

   override suspend fun getCurrentWeather(lat: String, long:String): Resource<WeatherResponse> = withContext(ioDispatcher){
        return@withContext try {
            Resource.Loading
            val response = api.getCurrentWeather(latitude = lat, longitude = long)
            delay(200)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it)
                } ?: Resource.Error(NO_DATA)
            } else {
                Resource.Error(NO_DATA)
            }
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: SOMETHING_BAD_HAPPENED)
        }
    }

    override suspend fun getForecastWeather(lat: String, long: String): Resource<ForecastResponse> = withContext(ioDispatcher){
        return@withContext try {
            Resource.Loading
            val response = api.getForecastWeather(latitude = lat, longitude = long)
            delay(200)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it)
                } ?: Resource.Error(NO_DATA)
            } else {
                Resource.Error(NO_DATA)
            }
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: SOMETHING_BAD_HAPPENED)
        }
    }

    companion object{
        const val NO_DATA = "No Data!"
        const val SOMETHING_BAD_HAPPENED = "Something bad happened.."
    }
}
