package com.muhammetkdr.weatherapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import java.util.*

@Module
@InstallIn(ViewModelComponent::class)
object CalendarModule {

    @Provides
    @ViewModelScoped
    fun provideCalender(): Calendar = Calendar.getInstance()

}