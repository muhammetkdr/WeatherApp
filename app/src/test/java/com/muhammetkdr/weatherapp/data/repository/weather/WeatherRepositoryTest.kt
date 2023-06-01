package com.muhammetkdr.weatherapp.data.repository.weather

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.muhammetkdr.weatherapp.LAT
import com.muhammetkdr.weatherapp.LON
import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.data.api.weather.WeatherAPIService
import com.muhammetkdr.weatherapp.data.mapper.CurrentWeatherMapperImpl
import com.muhammetkdr.weatherapp.data.mapper.ForecastWeatherMapperImpl
import com.muhammetkdr.weatherapp.data.remote.weather.WeatherRemoteDataSource
import com.muhammetkdr.weatherapp.data.remote.weather.WeatherRemoteDataSourceImpl
import com.muhammetkdr.weatherapp.domain.repository.weather.WeatherRepository
import com.muhammetkdr.weatherapp.mockCurrentWeatherResponse
import com.muhammetkdr.weatherapp.mockForecastWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class WeatherRepositoryTest {

    @Mock
    private lateinit var weatherApi: WeatherAPIService

    private lateinit var remoteDataSource: WeatherRemoteDataSource
    private lateinit var weatherRepository: WeatherRepository

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        remoteDataSource = WeatherRemoteDataSourceImpl(weatherApi,Dispatchers.Unconfined)
        weatherRepository = WeatherRepositoryImpl(
            weatherRemoteDataSource = remoteDataSource,
            currentWeatherMapper = CurrentWeatherMapperImpl(),
            forecastWeatherMapper = ForecastWeatherMapperImpl(),
            ioDispatcher = Dispatchers.Unconfined
        )
    }

    /**
     * Current Weather
     * */

    @Test
    fun`current weather response when api return success is response state success`(){
        runBlocking {
            Mockito.`when`(weatherApi.getCurrentWeather(latitude = LAT, longitude = LON)).thenReturn(mockCurrentWeatherResponse)
            weatherRepository.getCurrentWeather(lat = LAT, long = LON).test {
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Success::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun`current weather response when api return error is response state error`(){
        runBlocking {
            Mockito.`when`(weatherApi.getCurrentWeather(latitude = LAT, longitude = LON)).thenReturn(null)
            weatherRepository.getCurrentWeather(lat = LAT, long = LON).test {
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Error::class.java)
                awaitComplete()
            }
        }
    }

    /**
     * Forecast Weather
     * */

    @Test
    fun`forecast weather response when api return success is response state success`(){
        runBlocking {
            Mockito.`when`(weatherApi.getForecastWeather(latitude = LAT, longitude = LON)).thenReturn(mockForecastWeatherResponse)
            weatherRepository.getForecastWeather(lat = LAT, long = LON).test {
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Success::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun`forecast weather response when api return error is response state error`(){
        runBlocking {
            Mockito.`when`(weatherApi.getForecastWeather(latitude = LAT, longitude = LON)).thenReturn(null)
            weatherRepository.getForecastWeather(lat = LAT, long = LON).test {
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Error::class.java)
                awaitComplete()
            }
        }
    }

}