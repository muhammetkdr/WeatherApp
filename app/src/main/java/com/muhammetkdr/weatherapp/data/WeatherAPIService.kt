package com.muhammetkdr.weatherapp.data

import com.muhammetkdr.weatherapp.BuildConfig
import com.muhammetkdr.weatherapp.common.utils.Const.Companion.LANGUAGE
import com.muhammetkdr.weatherapp.common.utils.Const.Companion.UNITS
import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//  https://api.openweathermap.org/data/3.0/weather?&units=metric&lat={lat}&lon={lon}&appid={API key}
//  https://api.openweathermap.org/data/2.5/weather?lat={40}&lon={40}&appid=6f6f86438feb65a6bcaa6ee2aa15fe3b
//  https://api.openweathermap.org/data/3.0/weather?&units=metric&lat={39.922312}&lon={32.8578795}&appid={6f6f86438feb65a6bcaa6ee2aa15fe3b}

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