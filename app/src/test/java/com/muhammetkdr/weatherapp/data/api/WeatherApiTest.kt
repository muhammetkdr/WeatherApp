package com.muhammetkdr.weatherapp.data.api

import com.google.common.truth.Truth.assertThat
import com.muhammetkdr.weatherapp.CURRENT_WEATHER_RESPONSE_FILE_NAME
import com.muhammetkdr.weatherapp.FORECAST_WEATHER_RESPONSE_FILE_NAME
import com.muhammetkdr.weatherapp.LAT
import com.muhammetkdr.weatherapp.LON
import com.muhammetkdr.weatherapp.common.utils.Constants.LANGUAGE
import com.muhammetkdr.weatherapp.common.utils.Constants.METRIC
import com.muhammetkdr.weatherapp.data.api.weather.WeatherAPIService
import com.muhammetkdr.weatherapp.enqueueMockResponse
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherApiTest {
    private lateinit var weatherApi: WeatherAPIService
    private val mockWebServer = MockWebServer()

    @Before
    fun setup(){
        mockWebServer.start(8080)
        weatherApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherAPIService::class.java)
    }

    @Test
    fun `when current weather api response is not null`() = runBlocking {
        mockWebServer.enqueueMockResponse(CURRENT_WEATHER_RESPONSE_FILE_NAME)
        val response = weatherApi.getCurrentWeather(latitude = LAT, longitude = LON)
        assertThat(response).isNotNull()
    }

    @Test
    fun `when forecast weather api response is not null`() = runBlocking {
        mockWebServer.enqueueMockResponse(FORECAST_WEATHER_RESPONSE_FILE_NAME)
        val response = weatherApi.getForecastWeather(latitude = LAT, longitude = LON)
        assertThat(response).isNotNull()
    }

    @Test
    fun `requestPath when currentWeatherRequested is the same request`() = runBlocking {
        mockWebServer.enqueueMockResponse(CURRENT_WEATHER_RESPONSE_FILE_NAME)
        weatherApi.getCurrentWeather(latitude = LAT, longitude = LON)
        val request = mockWebServer.takeRequest()
        assertThat(request.path).isEqualTo("/data/2.5/weather?units=${METRIC}&lat=${LAT}&lon=${LON}&lang=${LANGUAGE}")
    }

    @Test
    fun `requestPath when forecastWeatherRequested is the same request`() = runBlocking {
        mockWebServer.enqueueMockResponse(CURRENT_WEATHER_RESPONSE_FILE_NAME)
        weatherApi.getForecastWeather(latitude = LAT, longitude = LON)
        val request = mockWebServer.takeRequest()
        assertThat(request.path).isEqualTo("/data/2.5/forecast?units=${METRIC}&lat=${LAT}&lon=${LON}&lang=${LANGUAGE}")
    }

    @Test
    fun `when the current weather first element the same with the requested`() = runBlocking{
        mockWebServer.enqueueMockResponse(CURRENT_WEATHER_RESPONSE_FILE_NAME)
        val response = weatherApi.getCurrentWeather(latitude = LAT, longitude = LON)
        val id =response.body()?.id
        val name = response.body()?.name
        assertThat(id).isEqualTo(317109)
        assertThat(name).isEqualTo("Denizli")
    }

    @Test
    fun `when the forecast weather first element the same with the requested`() = runBlocking{
        mockWebServer.enqueueMockResponse(FORECAST_WEATHER_RESPONSE_FILE_NAME)
        val response = weatherApi.getForecastWeather(latitude = LAT, longitude = LON)
        val temp = response.body()?.list?.get(0)?.main?.temp
        val name = response.body()?.city?.name
        assertThat(temp).isEqualTo(24.24)
        assertThat(name).isEqualTo("Denizli")
    }

    @After
    fun shutDown() {
        mockWebServer.shutdown()
    }
}