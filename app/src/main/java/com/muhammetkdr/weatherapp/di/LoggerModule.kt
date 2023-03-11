package com.muhammetkdr.weatherapp.di

import com.muhammetkdr.weatherapp.common.logger.Logger
import com.muhammetkdr.weatherapp.common.logger.LoggerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
abstract class LoggerModule {

    @Binds
    @ActivityScoped
    abstract fun bindLogger(loggerImpl : LoggerImpl): Logger

}