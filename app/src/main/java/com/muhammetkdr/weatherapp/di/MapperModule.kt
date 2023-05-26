package com.muhammetkdr.weatherapp.di

import com.muhammetkdr.weatherapp.data.dto.city.CitiesResponse
import com.muhammetkdr.weatherapp.data.dto.current.WeatherResponse
import com.muhammetkdr.weatherapp.data.dto.forecast.ForecastResponse
import com.muhammetkdr.weatherapp.data.listmapper.CitiesListMapperImpl
import com.muhammetkdr.weatherapp.data.listmapper.ListMapper
import com.muhammetkdr.weatherapp.data.mapper.CurrentWeatherMapperImpl
import com.muhammetkdr.weatherapp.data.mapper.ForecastWeatherMapperImpl
import com.muhammetkdr.weatherapp.data.mapper.WeatherMapper
import com.muhammetkdr.weatherapp.domain.entity.cities.CitiesEntity
import com.muhammetkdr.weatherapp.domain.entity.currentweather.CurrentWeatherEntity
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.ForecastWeatherEntity
import com.muhammetkdr.weatherapp.ui.home.HomeCurrentWeatherUiData
import com.muhammetkdr.weatherapp.ui.home.HomeCurrentWeatherUiMapperImpl
import com.muhammetkdr.weatherapp.ui.home.HomeForecastWeatherUiData
import com.muhammetkdr.weatherapp.ui.home.HomeForecastWeatherUiMapperImpl
import com.muhammetkdr.weatherapp.ui.search.SearchUiData
import com.muhammetkdr.weatherapp.ui.search.SearchUiDataMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class MapperModule {

    @Binds
    @ViewModelScoped
    abstract fun bindResponseToCurrentEntityMapper(currentWeatherMapperImpl: CurrentWeatherMapperImpl): WeatherMapper<WeatherResponse, CurrentWeatherEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindResponseToForecastEntityMapper(forecastWeatherMapperImpl: ForecastWeatherMapperImpl) : WeatherMapper<ForecastResponse, ForecastWeatherEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindResponseToCityEntityMapper(cityListMapperImpl: CitiesListMapperImpl): ListMapper<CitiesResponse, CitiesEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindCurrentWeatherEntityToCurrentWeatherUiMapper(homeCurrentWeatherUiMapperImpl: HomeCurrentWeatherUiMapperImpl): WeatherMapper<CurrentWeatherEntity,HomeCurrentWeatherUiData>

    @Binds
    @ViewModelScoped
    abstract fun bindForecastWeatherEntityToForecastWeatherUiMapper(homeForecastWeatherUiMapperImpl: HomeForecastWeatherUiMapperImpl): WeatherMapper<ForecastWeatherEntity,HomeForecastWeatherUiData>

    @Binds
    @ViewModelScoped
    abstract fun bindCitiesEntityToSearchUiDataMapper(searchUiDataMapperImpl: SearchUiDataMapperImpl) : ListMapper<CitiesEntity, SearchUiData>
}