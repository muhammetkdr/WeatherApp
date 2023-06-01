package com.muhammetkdr.weatherapp.data.repository.city

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.data.api.city.CityApi
import com.muhammetkdr.weatherapp.data.listmapper.CitiesListMapperImpl
import com.muhammetkdr.weatherapp.data.remote.city.CityRemoteDataSource
import com.muhammetkdr.weatherapp.data.remote.city.CityRemoteDataSourceImpl
import com.muhammetkdr.weatherapp.domain.repository.city.CityRepository
import com.muhammetkdr.weatherapp.mockCityResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class CityRepositoryTest {

    @Mock
    private lateinit var cityApi: CityApi

    private lateinit var remoteDataSource: CityRemoteDataSource
    private lateinit var cityRepository: CityRepository

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        remoteDataSource = CityRemoteDataSourceImpl(cityApi,Dispatchers.Unconfined)
        cityRepository = CityRepositoryImpl(remoteDataSource,CitiesListMapperImpl(),Dispatchers.Unconfined)
    }



    @Test
    fun`city response when api return success is response state success`(){
        runBlocking {
            Mockito.`when`(cityApi.getCityResponse()).thenReturn(mockCityResponse)
            cityRepository.getAllCities().test {
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Success::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun`city response when api return error is response state error`(){
        runBlocking {
            Mockito.`when`(cityApi.getCityResponse()).thenReturn(null)
            cityRepository.getAllCities().test {
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(Resource.Error::class.java)
                awaitComplete()
            }
        }
    }

}