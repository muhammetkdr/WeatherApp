package com.muhammetkdr.weatherapp.di

import com.muhammetkdr.weatherapp.domain.usecase.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun bindCurrentWeatherUseCase(currentWeatherUseCaseImpl: CurrentWeatherUseCaseImpl): CurrentWeatherUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindForecastWeatherListUseCase(forecastWeatherListUseCaseImpl: ForecastWeatherUseCaseImpl): ForecastWeatherUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindWeatherListUseCase(weatherListUseCaseImpl: WeatherListUseCaseImpl): WeatherListUseCase
}