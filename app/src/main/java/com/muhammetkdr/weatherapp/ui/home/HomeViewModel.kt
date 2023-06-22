package com.muhammetkdr.weatherapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammetkdr.weatherapp.common.extensions.component1
import com.muhammetkdr.weatherapp.common.extensions.component2
import com.muhammetkdr.weatherapp.common.extensions.component3
import com.muhammetkdr.weatherapp.common.extensions.formatCalendar
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
    private val defaultLocationClient: DefaultLocationClient
) : ViewModel() {

    private val _currentWeather: MutableStateFlow<UiState<HomeCurrentWeatherUiData>> =
        MutableStateFlow(UiState.Loading)
    val currentWeather: StateFlow<UiState<HomeCurrentWeatherUiData>>
        get() = _currentWeather

    private val _forecastWeather: MutableStateFlow<UiState<HomeForecastWeatherUiData>> =
        MutableStateFlow(UiState.Loading)
    val forecastWeather: StateFlow<UiState<HomeForecastWeatherUiData>>
        get() = _forecastWeather

    private val _date: MutableLiveData<String> = MutableLiveData("")
    val date: LiveData<String>
        get() = _date

    fun getTodaysCalendar(calendar: Calendar) {
        val (day, month, year) = calendar
        val dayFormatted = day.toString().formatCalendar()
        val monthFormatted = month.toString().formatCalendar()
        _date.value = "$dayFormatted.$monthFormatted.$year"
    }

    fun getCurrentLocation() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                defaultLocationClient.getLocationUpdates(LOCATION_REQUEST_DURATION).collect {
                    val latitude = it.latitude
                    val longitude = it.longitude
                    getMappedCurrentWeather(latitude, longitude)
                    getMappedForecastWeather(latitude, longitude)
                }
            } catch (e: Exception) {
                println(e.localizedMessage ?: "gps is disabled")
            }
        }
    }

    fun getMappedCurrentWeather(lat: Double, long: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            val latitude = lat.toString()
            val longitude = long.toString()
            currentWeatherUseCase.invoke(latitude, longitude).collect {
                currentWeatherDataMapperHandler(it)
            }
        }
    }

    fun getMappedForecastWeather(lat: Double, long: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            val latitude = lat.toString()
            val longitude = long.toString()

            forecastWeatherUseCase.invoke(latitude, longitude).collect {
                forecastWeatherDataMapperHandler(it)
            }
        }
    }

    fun getMappedCurrentWeather(lat: String, long: String) {
        viewModelScope.launch(Dispatchers.IO) {
            currentWeatherUseCase.invoke(lat, long).collect {
                currentWeatherDataMapperHandler(it)
            }
        }
    }

    fun getMappedForecastWeather(lat: String, long: String) {
        viewModelScope.launch(Dispatchers.IO) {
            forecastWeatherUseCase.invoke(lat, long).collect {
                forecastWeatherDataMapperHandler(it)
            }
        }
    }

    private fun currentWeatherDataMapperHandler(currentWeatherData: Resource<CurrentWeatherEntity>) {
        viewModelScope.launch(Dispatchers.IO) {
            when (currentWeatherData) {
                is Resource.Error -> {
                    _currentWeather.emit(UiState.Error(currentWeatherData.error))
                }

                is Resource.Loading -> _currentWeather.emit(UiState.Loading)
                is Resource.Success -> {
                    _currentWeather.emit(UiState.Success(currentWeatherMapper.map(currentWeatherData.data)))
                }
            }
        }
    }

    private fun forecastWeatherDataMapperHandler(forecastWeatherData: Resource<ForecastWeatherEntity>) {
        viewModelScope.launch(Dispatchers.IO) {
            when (forecastWeatherData) {
                is Resource.Error -> {
                    _forecastWeather.emit(UiState.Error(forecastWeatherData.error))
                }

                is Resource.Loading -> _forecastWeather.emit(UiState.Loading)
                is Resource.Success -> {
                    _forecastWeather.emit(
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

}