package com.muhammetkdr.weatherapp.di

import com.muhammetkdr.weatherapp.data.dto.current.WeatherResponse
import com.muhammetkdr.weatherapp.domain.entity.CurrentWeatherEntity
import com.muhammetkdr.weatherapp.domain.mapper.CurrentWeatherMapperImpl
import com.muhammetkdr.weatherapp.domain.mapper.WeatherMapper
import com.muhammetkdr.weatherapp.ui.home.uidata.CurrentWeatherUiModel
import com.muhammetkdr.weatherapp.ui.home.uidata.CurrentWeatherUiModelMapper
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
    abstract fun bindResponseToEntityMapper(currentWeatherMapperImpl: CurrentWeatherMapperImpl): WeatherMapper<WeatherResponse, CurrentWeatherEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindEntityToUiMapper(currentWeatherUiModelMapper: CurrentWeatherUiModelMapper): WeatherMapper<CurrentWeatherEntity, CurrentWeatherUiModel>

}