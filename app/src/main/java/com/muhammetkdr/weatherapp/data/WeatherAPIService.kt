package com.muhammetkdr.weatherapp.data

import com.muhammetkdr.weatherapp.BuildConfig
import com.muhammetkdr.weatherapp.common.utils.Const.Companion.LANGUAGE
import com.muhammetkdr.weatherapp.common.utils.Const.Companion.UNITS
import com.muhammetkdr.weatherapp.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPIService {

    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("units") unit: String = UNITS,
        @Query("lat") latitude : String,
        @Query("lon") longitude : String,
        @Query("lang") language : String = LANGUAGE,
        @Query("appid") apiKey:String = BuildConfig.API_KEY
    ) : Response<WeatherResponse>

}