package com.muhammetkdr.weatherapp.di

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.muhammetkdr.weatherapp.location.DefaultLocationClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocationClientModule {

    @Provides
    @Singleton
    fun provideDefaultClient(
        @ApplicationContext context: Context,
        client: FusedLocationProviderClient,
    ) = DefaultLocationClient(context, client)

    @Provides
    @Singleton
    fun provideFusedLocation(
        @ApplicationContext context: Context
    ) = LocationServices.getFusedLocationProviderClient(context)

}