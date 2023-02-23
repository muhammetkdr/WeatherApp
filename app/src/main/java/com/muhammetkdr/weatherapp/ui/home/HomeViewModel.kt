package com.muhammetkdr.weatherapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.domain.entity.CurrentWeatherEntity
import com.muhammetkdr.weatherapp.domain.entity.ForecastWeatherEntity
import com.muhammetkdr.weatherapp.domain.usecase.CurrentWeatherUseCase
import com.muhammetkdr.weatherapp.domain.usecase.ForecastWeatherUseCase
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
    private val forecastWeatherUseCase: ForecastWeatherUseCase
) : ViewModel() {

    private val _currentWeatherMapped: MutableStateFlow<Resource<CurrentWeatherEntity>> = MutableStateFlow(Resource.Loading)
    val currentWeatherMapped: StateFlow<Resource<CurrentWeatherEntity>> get() = _currentWeatherMapped.asStateFlow()

    private val _forecastWeatherMapped: MutableStateFlow<Resource<ForecastWeatherEntity>> = MutableStateFlow(Resource.Loading)
    val forecastWeatherMapped: StateFlow<Resource<ForecastWeatherEntity>> get() = _forecastWeatherMapped.asStateFlow()

    fun getMappedWeather(lat: Double, long: Double) = viewModelScope.launch(Dispatchers.IO) {
        val latitude = lat.toString()
        val longitude = long.toString()

        currentWeatherUseCase.invoke(latitude, longitude).collect {
            _currentWeatherMapped.emit(it)
        }
    }

    fun getMappedForecastWeather(lat: Double, long: Double) = viewModelScope.launch(Dispatchers.IO) {
        val latitude = lat.toString()
        val longitude = long.toString()

        forecastWeatherUseCase.invoke(latitude, longitude).collect {
            _forecastWeatherMapped.emit(it)
        }
    }
}