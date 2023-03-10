package com.muhammetkdr.weatherapp.di

import com.muhammetkdr.weatherapp.common.logger.Logger
import com.muhammetkdr.weatherapp.common.logger.LoggerImpl
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.DatesAndTimes
import com.muhammetkdr.weatherapp.ui.home.nestedrv.HomeParentForecastWeatherAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.components.SingletonComponent
import java.util.Calendar
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLogger() :Logger = LoggerImpl()

}