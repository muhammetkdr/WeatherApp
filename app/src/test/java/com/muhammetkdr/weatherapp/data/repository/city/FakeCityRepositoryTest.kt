package com.muhammetkdr.weatherapp.data.repository.city

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.muhammetkdr.weatherapp.common.utils.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Test

class FakeCityRepositoryTest {

    private val fakeCityRepository = FakeCityRepository()

    @Test
    fun cityResponse_whenRemoteDataSourceReturnSuccess_returnSuccess(){
        runBlocking {
            fakeCityRepository.getAllCities().test {
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Success::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun cityResponse_whenRemoteDataSourceReturnError_returnError(){
        runBlocking {
            fakeCityRepository.updateShowError(true)
            fakeCityRepository.getAllCities().test {
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Error::class.java)
                awaitComplete()
            }
        }
    }
}