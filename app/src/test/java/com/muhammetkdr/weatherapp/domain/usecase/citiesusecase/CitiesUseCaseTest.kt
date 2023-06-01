package com.muhammetkdr.weatherapp.domain.usecase.citiesusecase

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.muhammetkdr.weatherapp.common.utils.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CitiesUseCaseTest {

    private val fakeCitiesUseCase = FakeCitiesUseCase()

    @Test
    fun networkState_whenStateLoading_returnLoading() {
        runBlocking {
            fakeCitiesUseCase().test {
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun networkState_whenStateLoadingAndSuccess_returnLoadingAndSuccessSequentially() {
        runBlocking {
            fakeCitiesUseCase().test {
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Success::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun networkState_whenStateLoadingAndError_returnError() {
        runBlocking {
            fakeCitiesUseCase.updateShowError(true)
            fakeCitiesUseCase().test {
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Error::class.java)
                awaitComplete()
            }
        }
    }

}