package com.muhammetkdr.weatherapp.domain.usecase.forecastweatherusecase

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.muhammetkdr.weatherapp.LAT
import com.muhammetkdr.weatherapp.LON
import com.muhammetkdr.weatherapp.common.utils.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ForecastWeatherUseCaseTest {

    private val fakeForecastWeatherUseCase = FakeForecastWeatherUseCase()

    @Test
    fun networkState_whenStateLoading_returnLoading() {
        runBlocking {
            fakeForecastWeatherUseCase(LAT, LON).test {
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun networkState_whenStateLoadingAndSuccess_returnLoadingAndSuccessSequentially() {
        runBlocking {
            fakeForecastWeatherUseCase(LAT, LON).test {
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Success::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun networkState_whenStateLoadingAndError_returnError() {
        runBlocking {
            fakeForecastWeatherUseCase.updateShowError(true)
            fakeForecastWeatherUseCase(LAT, LON).test {
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Error::class.java)
                awaitComplete()
            }
        }
    }
}