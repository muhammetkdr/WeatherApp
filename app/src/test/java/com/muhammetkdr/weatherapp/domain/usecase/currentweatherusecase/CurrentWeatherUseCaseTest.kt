package com.muhammetkdr.weatherapp.domain.usecase.currentweatherusecase

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.muhammetkdr.weatherapp.LAT
import com.muhammetkdr.weatherapp.LON
import com.muhammetkdr.weatherapp.common.utils.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CurrentWeatherUseCaseTest {

    private val fakeCurrentWeatherUseCase = FakeCurrentWeatherUseCase()

    @Test
    fun networkState_whenStateLoading_returnLoading() {
        runBlocking {
            fakeCurrentWeatherUseCase(LAT, LON).test {
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun networkState_whenStateLoadingAndSuccess_returnLoadingAndSuccessSequentially() {
        runBlocking {
            fakeCurrentWeatherUseCase(LAT, LON).test {
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Success::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun networkState_whenStateLoadingAndError_returnError() {
        runBlocking {
            fakeCurrentWeatherUseCase.updateShowError(true)
            fakeCurrentWeatherUseCase(LAT, LON).test {
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Error::class.java)
                awaitComplete()
            }
        }
    }

}