package com.muhammetkdr.weatherapp.di

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.muhammetkdr.weatherapp.location.DefaultLocationClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.FragmentScoped


@Module
@InstallIn(FragmentComponent::class)
object LocationClientModule {

    @Provides
    @FragmentScoped
    fun provideDefaultClient(
        @ApplicationContext context: Context,
        client: FusedLocationProviderClient,
    ) = DefaultLocationClient(context, client)

    @Provides
    @FragmentScoped
    fun provideFusedLocation(
        @ApplicationContext context: Context
    ) = LocationServices.getFusedLocationProviderClient(context)

}