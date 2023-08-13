package com.muhammetkdr.weatherapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammetkdr.weatherapp.R
import com.muhammetkdr.weatherapp.common.extensions.EMPTY
import com.muhammetkdr.weatherapp.common.extensions.component1
import com.muhammetkdr.weatherapp.common.extensions.component2
import com.muhammetkdr.weatherapp.common.extensions.component3
import com.muhammetkdr.weatherapp.common.extensions.formatCalendar
import com.muhammetkdr.weatherapp.common.extensions.collectInViewModelScope
import com.muhammetkdr.weatherapp.common.utils.Constants.LOCATION_REQUEST_DURATION
import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.data.mapper.WeatherMapper
import com.muhammetkdr.weatherapp.domain.entity.currentweather.CurrentWeatherEntity
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.ForecastWeatherEntity
import com.muhammetkdr.weatherapp.domain.usecase.CurrentWeatherUseCase
import com.muhammetkdr.weatherapp.domain.usecase.ForecastWeatherUseCase
import com.muhammetkdr.weatherapp.location.DefaultLocationClient
import com.muhammetkdr.weatherapp.ui.uistate.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val currentWeatherUseCase: CurrentWeatherUseCase,
    private val forecastWeatherUseCase: ForecastWeatherUseCase,
    private val currentWeatherMapper: WeatherMapper<CurrentWeatherEntity, HomeCurrentWeatherUiData>,
    private val forecastWeatherMapper: WeatherMapper<ForecastWeatherEntity, HomeForecastWeatherUiData>,
    private val defaultLocationClient: DefaultLocationClient,
    private val calendar: Calendar
) : ViewModel() {

    private val _currentWeather: MutableStateFlow<UiState<HomeCurrentWeatherUiData>> =
        MutableStateFlow(UiState.Loading)
    val currentWeather: StateFlow<UiState<HomeCurrentWeatherUiData>>
        get() = _currentWeather

    private val _forecastWeather: MutableStateFlow<UiState<HomeForecastWeatherUiData>> =
        MutableStateFlow(UiState.Loading)
    val forecastWeather: StateFlow<UiState<HomeForecastWeatherUiData>>
        get() = _forecastWeather

    private val _date: MutableStateFlow<String> = MutableStateFlow(String.EMPTY)
    val date: StateFlow<String>
        get() = _date

    private val _gpsError: MutableStateFlow<Int?> = MutableStateFlow(null)
    val gpsError: StateFlow<Int?>
        get() = _gpsError

    fun getTodaysCalendar() = viewModelScope.launch(Dispatchers.IO) {
        val (day, month, year) = calendar
        val dayFormatted = day.toString().formatCalendar()
        val monthFormatted = month.toString().formatCalendar()
        _date.emit("$dayFormatted.$monthFormatted.$year")
    }

    fun getCurrentLocation() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                defaultLocationClient.getLocationUpdates(LOCATION_REQUEST_DURATION).collect {
                    getMappedCurrentWeather(it.latitude, it.longitude)
                    getMappedForecastWeather(it.latitude, it.longitude)
                }
            } catch (e: Exception) {
                _gpsError.emit(R.string.gps_orNetwork_disabled)
            }
        }
    }

    //function overload
    fun getMappedCurrentWeather(lat: Double, long: Double) {
            collectInViewModelScope(currentWeatherUseCase.invoke(lat.toString(), long.toString())) {
                currentWeatherDataMapperHandler(it)
            }
    }

    fun getMappedForecastWeather(lat: Double, long: Double) {
            collectInViewModelScope(forecastWeatherUseCase.invoke(lat.toString(), long.toString())) {
                forecastWeatherDataMapperHandler(it)
            }
    }

    //function overload
    fun getMappedCurrentWeather(lat: String, long: String) {
            collectInViewModelScope(currentWeatherUseCase.invoke(lat, long)){
                currentWeatherDataMapperHandler(it)
            }
    }

    fun getMappedForecastWeather(lat: String, long: String) {
        collectInViewModelScope(forecastWeatherUseCase.invoke(lat,long)){
            forecastWeatherDataMapperHandler(it)
        }
    }

    private fun currentWeatherDataMapperHandler(currentWeatherData: Resource<CurrentWeatherEntity>) {
        viewModelScope.launch(Dispatchers.IO) {
            when (currentWeatherData) {
                is Resource.Error -> _currentWeather.emit(UiState.Error(currentWeatherData.error))
                is Resource.Loading -> _currentWeather.emit(UiState.Loading)
                is Resource.Success -> _currentWeather.emit(
                    UiState.Success(
                        currentWeatherMapper.map(
                            currentWeatherData.data
                        )
                    )
                )
            }
        }
    }

    private fun forecastWeatherDataMapperHandler(forecastWeatherData: Resource<ForecastWeatherEntity>) {
        viewModelScope.launch(Dispatchers.IO) {
            when (forecastWeatherData) {
                is Resource.Error -> _forecastWeather.emit(UiState.Error(forecastWeatherData.error))
                is Resource.Loading -> _forecastWeather.emit(UiState.Loading)
                is Resource.Success -> _forecastWeather.emit(
                    UiState.Success(
                        forecastWeatherMapper.map(
                            forecastWeatherData.data
                        )
                    )
                )
            }
        }
    }
}