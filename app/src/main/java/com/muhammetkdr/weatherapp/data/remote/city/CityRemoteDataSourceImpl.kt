package com.muhammetkdr.weatherapp.data.remote.city

import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.data.api.city.CityApi
import com.muhammetkdr.weatherapp.data.dto.city.CitiesResponse
import com.muhammetkdr.weatherapp.di.Dispatcher
import com.muhammetkdr.weatherapp.di.DispatcherType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CityRemoteDataSourceImpl @Inject constructor(
    private val cityApi: CityApi,
    @Dispatcher(DispatcherType.Io) private val ioDispatcher: CoroutineContext
) : CityRemoteDataSource {

    override fun getCityResponse(): Flow<Resource<List<CitiesResponse>>> = flow {
            try {
            emit(Resource.Loading)
            val response = cityApi.getCityResponse()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Resource.Success(it))
                } ?: emit(Resource.Error(NO_DATA))
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