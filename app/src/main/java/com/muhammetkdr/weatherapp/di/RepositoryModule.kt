package com.muhammetkdr.weatherapp.di

import com.muhammetkdr.weatherapp.data.remote.city.CityRemoteDataSource
import com.muhammetkdr.weatherapp.data.remote.city.CityRemoteDataSourceImpl
import com.muhammetkdr.weatherapp.data.remote.weather.WeatherRemoteDataSource
import com.muhammetkdr.weatherapp.data.remote.weather.WeatherRemoteDataSourceImpl
import com.muhammetkdr.weatherapp.domain.repository.city.CityRepository
import com.muhammetkdr.weatherapp.domain.repository.city.CityRepositoryImpl
import com.muhammetkdr.weatherapp.domain.repository.weather.WeatherRepository
import com.muhammetkdr.weatherapp.domain.repository.weather.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule{

    @Binds
    @ViewModelScoped
    abstract fun bindRemoteDataSource(remoteDataSource: WeatherRemoteDataSourceImpl): WeatherRemoteDataSource

    @Binds
    @ViewModelScoped
    abstract fun bindWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository

    @Binds
    @ViewModelScoped
    abstract fun bindCitiesRemoteDataSource(citiesRemoteDataSourceImpl: CityRemoteDataSourceImpl) : CityRemoteDataSource

    @Binds
    @ViewModelScoped
    abstract fun bindCityRepository(cityRepositoryImpl: CityRepositoryImpl) : CityRepository

}