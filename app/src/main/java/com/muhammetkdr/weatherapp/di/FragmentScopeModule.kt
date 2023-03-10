package com.muhammetkdr.weatherapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import java.util.*

@Module
@InstallIn(FragmentComponent::class)
object FragmentScopeModule {

    @Provides
    @FragmentScoped
    fun provideCalender(): Calendar = Calendar.getInstance()

}