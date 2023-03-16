package com.muhammetkdr.weatherapp.domain.repository.weather

import com.muhammetkdr.weatherapp.common.extensions.mapResource
import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.data.dto.current.WeatherResponse
import com.muhammetkdr.weatherapp.data.dto.forecast.ForecastResponse
import com.muhammetkdr.weatherapp.data.dto.forecast.WeatherList
import com.muhammetkdr.weatherapp.data.dto.search.SearchResponse
import com.muhammetkdr.weatherapp.data.remote.weather.WeatherRemoteDataSource
import com.muhammetkdr.weatherapp.domain.entity.currentweather.CurrentWeatherEntity
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.ForecastWeatherEntity
import com.muhammetkdr.weatherapp.domain.entity.weatherlist.WeatherListEntity
import com.muhammetkdr.weatherapp.data.listmapper.ListMapper
import com.muhammetkdr.weatherapp.data.mapper.WeatherMapper
import com.muhammetkdr.weatherapp.domain.entity.searchweather.SearchWeatherEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class WeatherRepositoryImpl @Inject constructor(
    private val weatherRemoteDataSource: WeatherRemoteDataSource,
    private val currentWeatherMapper: WeatherMapper<WeatherResponse, CurrentWeatherEntity>,
    private val forecastWeatherMapper: WeatherMapper<ForecastResponse, ForecastWeatherEntity>,
    private val searchWeatherMapper: WeatherMapper<SearchResponse, SearchWeatherEntity>,
    private val listMapper: ListMapper<WeatherList, WeatherListEntity>,
    private val ioDispatcher: CoroutineContext
) : WeatherRepository {

    override fun getCurrentWeather(lat: String, long: String): Flow<Resource<CurrentWeatherEntity>> =
        weatherRemoteDataSource.getCurrentWeather(lat, long).map {
            it.mapResource { currentWeatherMapper.map(this) }
        }.flowOn(ioDispatcher)

    override fun getForecastWeather(lat: String, long: String): Flow<Resource<ForecastWeatherEntity>> =
        weatherRemoteDataSource.getForecastWeather(lat, long).map {
            it.mapResource { forecastWeatherMapper.map(this) }
        }.flowOn(ioDispatcher)

    override fun getWeatherList(lat: String, long: String): Flow<Resource<List<WeatherListEntity>>> =
        weatherRemoteDataSource.getWeatherList(lat, long).map {
            it.mapResource { listMapper.map(this) }
        }.flowOn(ioDispatcher)

    override fun getSearchResponse(cityNameQuery: String): Flow<Resource<SearchWeatherEntity>> =
        weatherRemoteDataSource.getSearchResponse(cityNameQuery).map {
            it.mapResource { searchWeatherMapper.map(this) }
        }.flowOn(ioDispatcher)

}