package com.muhammetkdr.weatherapp.data.api

import com.muhammetkdr.weatherapp.BuildConfig
import com.muhammetkdr.weatherapp.common.utils.Constants.LANGUAGE
import com.muhammetkdr.weatherapp.common.utils.Constants.METRIC
import com.muhammetkdr.weatherapp.data.dto.current.WeatherResponse
import com.muhammetkdr.weatherapp.data.dto.forecast.ForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPIService {

    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("units") unit: String = METRIC,
        @Query("lat") latitude : String,
        @Query("lon") longitude : String,
        @Query("lang") language : String = LANGUAGE,
        @Query("appid") apiKey:String = BuildConfig.API_KEY
    ) : Response<WeatherResponse>

    @GET("data/2.5/forecast")
    suspend fun getForecastWeather(
        @Query("lat") latitude : String,
        @Query("lon") longitude : String,
        @Query("appid") apiKey:String = BuildConfig.API_KEY,
        @Query("units") unit: String = METRIC,
        @Query("lang") language : String = LANGUAGE
    ) : Response<ForecastResponse>

}