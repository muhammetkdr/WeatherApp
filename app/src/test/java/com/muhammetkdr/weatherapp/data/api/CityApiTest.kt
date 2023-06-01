package com.muhammetkdr.weatherapp.data.api

import com.google.common.truth.Truth.assertThat
import com.muhammetkdr.weatherapp.CITY_RESPONSE_FILE_NAME
import com.muhammetkdr.weatherapp.data.api.city.CityApi
import com.muhammetkdr.weatherapp.enqueueMockResponse
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CityApiTest {
    private lateinit var cityApi : CityApi
    private val mockWebServer = MockWebServer()

    @Before
    fun setup(){
        mockWebServer.start(8080)
        cityApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CityApi::class.java)

        mockWebServer.enqueueMockResponse(CITY_RESPONSE_FILE_NAME)
    }

    @Test
    fun `when city api response is not null`() = runBlocking {
        val response = cityApi.getCityResponse()
        assertThat(response).isNotNull()
    }
    @Test
    fun `requestPath when city requested is the same request`() = runBlocking {
        cityApi.getCityResponse()
        val request = mockWebServer.takeRequest()
        assertThat(request.path).isEqualTo("/mskdr/bcea4af3761537dbc81fa38812332a9e/raw/887716bdb0891bc5c1c8d1e93cdc9e5f818fa147/cities_of_turkey")
    }

    @Test
    fun `when the city first element the same with the requested`() = runBlocking{
        val response = cityApi.getCityResponse()
        val firstCityName = response.body()?.first()?.name
        val firstCityId = response.body()?.first()?.id
        assertThat(firstCityName).isEqualTo("Adana")
        assertThat(firstCityId).isEqualTo(1)
    }

    @After
    fun shotDown(){
        mockWebServer.shutdown()
    }
}