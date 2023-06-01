package com.muhammetkdr.weatherapp.data.source

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.muhammetkdr.weatherapp.LAT
import com.muhammetkdr.weatherapp.LON
import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.data.api.weather.WeatherAPIService
import com.muhammetkdr.weatherapp.data.remote.weather.WeatherRemoteDataSourceImpl
import com.muhammetkdr.weatherapp.mockCurrentWeatherResponse
import com.muhammetkdr.weatherapp.mockForecastWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class WeatherDataSourceTest {

    @Mock
    private lateinit var weatherAPI: WeatherAPIService

    private lateinit var weatherRemoteDataSource: WeatherRemoteDataSourceImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        weatherRemoteDataSource = WeatherRemoteDataSourceImpl(weatherAPI, Dispatchers.Unconfined)
    }

    /**
     * Current Weather
     */

    @Test
    fun `when currentWeather api request return success is response state success`() = runBlocking {
        Mockito.`when`(weatherAPI.getCurrentWeather(latitude = LAT, longitude = LON))
            .thenReturn(mockCurrentWeatherResponse)
        weatherRemoteDataSource.getCurrentWeather(lat = LAT, long = LON).test {
            assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
            assertThat(awaitItem()).isInstanceOf(Resource.Success::class.java)
            awaitComplete()
        }
    }

    @Test
    fun `when currentWeather api request return null is response state error`() = runBlocking {
        Mockito.`when`(weatherAPI.getCurrentWeather(latitude = LAT, longitude = LON))
            .thenReturn(null)
        weatherRemoteDataSource.getCurrentWeather(lat = LAT, long = LON).test {
            assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
            assertThat(awaitItem()).isInstanceOf(Resource.Error::class.java)
            awaitComplete()
        }
    }

    /**
     * Forecast Weather
     */
    @Test
    fun `when forecast api request return success is response state success`() = runBlocking {
        Mockito.`when`(weatherAPI.getForecastWeather(latitude = LAT, longitude = LON))
            .thenReturn(mockForecastWeatherResponse)
        weatherRemoteDataSource.getForecastWeather(lat = LAT, long = LON).test {
            assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
            assertThat(awaitItem()).isInstanceOf(Resource.Success::class.java)
            awaitComplete()
        }
    }

    @Test
    fun `when forecast api request return null is response state error`() = runBlocking {
        Mockito.`when`(weatherAPI.getForecastWeather(latitude = LAT, longitude = LON))
            .thenReturn(null)
        weatherRemoteDataSource.getForecastWeather(lat = LAT, long = LON).test {
            assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
            assertThat(awaitItem()).isInstanceOf(Resource.Error::class.java)
            awaitComplete()
        }
    }

}