package com.muhammetkdr.weatherapp.di

import com.muhammetkdr.weatherapp.data.dto.current.WeatherResponse
import com.muhammetkdr.weatherapp.data.dto.forecast.ForecastResponse
import com.muhammetkdr.weatherapp.domain.entity.CurrentWeatherEntity
import com.muhammetkdr.weatherapp.domain.entity.ForecastWeatherEntity
import com.muhammetkdr.weatherapp.domain.mapper.CurrentWeatherMapperImpl
import com.muhammetkdr.weatherapp.domain.mapper.ForecastWeatherMapperImpl
import com.muhammetkdr.weatherapp.domain.mapper.WeatherMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class MapperModule {

    @Binds
    @ViewModelScoped
    abstract fun bindResponseToCurrentEntityMapper(currentWeatherMapperImpl: CurrentWeatherMapperImpl): WeatherMapper<WeatherResponse, CurrentWeatherEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindResponseToForecastEntityMapper(forecastWeatherMapperImpl: ForecastWeatherMapperImpl) : WeatherMapper<ForecastResponse,ForecastWeatherEntity>

}