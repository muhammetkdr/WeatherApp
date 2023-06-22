package com.muhammetkdr.weatherapp.di

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.muhammetkdr.weatherapp.location.DefaultLocationClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object LocationClientModule {

    @Provides
    @ViewModelScoped
    fun provideDefaultClient(
        @ApplicationContext context: Context,
        client: FusedLocationProviderClient,
    ) = DefaultLocationClient(context, client)

    @Provides
    @ViewModelScoped
    fun provideFusedLocation(
        @ApplicationContext context: Context
    ) : FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

}