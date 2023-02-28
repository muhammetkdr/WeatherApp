package com.muhammetkdr.weatherapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.domain.entity.currentweather.CurrentWeatherEntity
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.ForecastWeatherEntity
import com.muhammetkdr.weatherapp.domain.entity.weatherlist.WeatherListEntity
import com.muhammetkdr.weatherapp.domain.usecase.CurrentWeatherUseCase
import com.muhammetkdr.weatherapp.domain.usecase.ForecastWeatherUseCase
import com.muhammetkdr.weatherapp.domain.usecase.WeatherListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val currentWeatherUseCase: CurrentWeatherUseCase,
    private val forecastWeatherUseCase: ForecastWeatherUseCase,
    private val weatherListUseCase: WeatherListUseCase
) : ViewModel() {

    private val _currentWeather: MutableStateFlow<Resource<CurrentWeatherEntity>> = MutableStateFlow(Resource.Loading)
    val currentWeather: StateFlow<Resource<CurrentWeatherEntity>> get() = _currentWeather.asStateFlow()

    private val _forecastWeather: MutableStateFlow<Resource<ForecastWeatherEntity>> = MutableStateFlow(Resource.Loading)
    val forecastWeather: StateFlow<Resource<ForecastWeatherEntity>> get() = _forecastWeather.asStateFlow()

    private val _weatherList: MutableStateFlow<Resource<List<WeatherListEntity>>> = MutableStateFlow(Resource.Loading)
    val weatherList: StateFlow<Resource<List<WeatherListEntity>>> get() = _weatherList.asStateFlow()

    fun getMappedCurrentWeather(lat: Double, long: Double) = viewModelScope.launch(Dispatchers.IO) {
        val latitude = lat.toString()
        val longitude = long.toString()

        currentWeatherUseCase.invoke(latitude, longitude).collect {
            _currentWeather.emit(it)
        }
    }

    fun getMappedForecastWeather(lat: Double, long: Double) = viewModelScope.launch(Dispatchers.IO) {
        val latitude = lat.toString()
        val longitude = long.toString()

        forecastWeatherUseCase.invoke(latitude, longitude).collect {
            _forecastWeather.emit(it)
        }
    }

    fun getWeatherList(lat:Double, long: Double) = viewModelScope.launch(Dispatchers.IO){
        val latitude = lat.toString()
        val longitude = long.toString()

        weatherListUseCase.invoke(latitude,longitude).collect{
            _weatherList.emit(it)
        }
    }

}