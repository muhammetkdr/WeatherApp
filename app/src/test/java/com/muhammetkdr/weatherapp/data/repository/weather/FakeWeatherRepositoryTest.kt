package com.muhammetkdr.weatherapp.data.repository.weather

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.muhammetkdr.weatherapp.LAT
import com.muhammetkdr.weatherapp.LON
import com.muhammetkdr.weatherapp.common.utils.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Test

class FakeWeatherRepositoryTest {

    private val fakeWeatherRepository = FakeWeatherRepository()

    /**
    *  Current Weather Test
    */

    @Test
    fun currentWeatherResponse_whenRemoteDataSourceReturnSuccess_returnSuccess(){
        runBlocking {
            fakeWeatherRepository.getCurrentWeather(LAT, LON).test {
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Success::class.java)
                awaitComplete()
            }
        }
    }
    @Test
    fun currentWeatherResponse_whenRemoteDataSourceReturnError_returnError(){
        runBlocking {
            fakeWeatherRepository.updateShowErrorForCurrentWeather(true)
            fakeWeatherRepository.getCurrentWeather(LAT, LON).test {
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Error::class.java)
                awaitComplete()
            }
        }
    }

    /**
     *  Forecast Weather Test
     */
    @Test
    fun forecastWeatherResponse_whenRemoteDataSourceReturnSuccess_returnSuccess(){
        runBlocking {
            fakeWeatherRepository.getForecastWeather(LAT, LON).test {
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Success::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun forecastWeatherResponse_whenRemoteDataSourceReturnError_returnError(){
        runBlocking {
            fakeWeatherRepository.updateShowErrorForForecastWeather(true)
            fakeWeatherRepository.getForecastWeather(LAT, LON).test {
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Error::class.java)
                awaitComplete()
            }
        }
    }

}