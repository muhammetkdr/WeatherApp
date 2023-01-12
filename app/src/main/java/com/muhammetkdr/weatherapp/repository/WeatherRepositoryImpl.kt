package com.muhammetkdr.weatherapp.repository

import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.data.WeatherAPIService
import com.muhammetkdr.weatherapp.model.WeatherResponse
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherAPIService,
    private val ioDispatcher:CoroutineContext
) : WeatherRepository{

   override suspend fun getCurrentWeather(lat: String, long:String): Resource<WeatherResponse> = withContext(ioDispatcher){
        return@withContext try {
            Resource.Loading
            val response = api.getCurrentWeather(latitude = lat, longitude = long)
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
