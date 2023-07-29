package com.muhammetkdr.weatherapp.data.source.remote

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.data.api.city.CityApi
import com.muhammetkdr.weatherapp.data.remote.city.CityRemoteDataSourceImpl
import com.muhammetkdr.weatherapp.mockCityResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class CityDataSourceTest {

    @Mock
    private lateinit var cityApi: CityApi
    private lateinit var cityRemoteDataSource: CityRemoteDataSourceImpl

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        cityRemoteDataSource = CityRemoteDataSourceImpl(cityApi, Dispatchers.Unconfined)
    }

    @Test
    fun `when city api request return success is response state success`() = runBlocking {
        Mockito.`when`(cityApi.getCityResponse()).thenReturn(mockCityResponse)
        cityRemoteDataSource.getCityResponse().test {
            assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
            assertThat(awaitItem()).isInstanceOf(Resource.Success::class.java)
            awaitComplete()
        }
    }

    @Test
    fun `when city api request return null is response state error`() = runBlocking {
        Mockito.`when`(cityApi.getCityResponse()).thenReturn(null)
        cityRemoteDataSource.getCityResponse().test {
            assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
            assertThat(awaitItem()).isInstanceOf(Resource.Error::class.java)
            awaitComplete()
        }
    }

}
