package com.muhammetkdr.weatherapp.di

import com.muhammetkdr.weatherapp.common.logger.Logger
import com.muhammetkdr.weatherapp.common.logger.LoggerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.android.scopes.ViewModelScoped
import java.util.Calendar

@Module
@InstallIn(FragmentComponent::class)
object AppModule {

    @Provides
    @FragmentScoped
    fun provideLogger() :Logger = LoggerImpl()

    @Provides
    @FragmentScoped
    fun provideCallender(): Calendar = Calendar.getInstance()

}