package com.muhammetkdr.weatherapp.di

import com.muhammetkdr.weatherapp.data.remote.RemoteDataSource
import com.muhammetkdr.weatherapp.data.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class WeatherRepositoryModule{

    @Binds
    @ViewModelScoped
    abstract fun bindRemoteDataSource(remoteDataSource: RemoteDataSourceImpl): RemoteDataSource


}