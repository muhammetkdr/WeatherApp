package com.muhammetkdr.weatherapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammetkdr.weatherapp.common.extensions.component1
import com.muhammetkdr.weatherapp.common.extensions.component2
import com.muhammetkdr.weatherapp.common.extensions.component3
import com.muhammetkdr.weatherapp.common.extensions.formatCalendar
import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.domain.entity.currentweather.CurrentWeatherEntity
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.ForecastWeatherEntity
import com.muhammetkdr.weatherapp.domain.usecase.CurrentWeatherUseCase
import com.muhammetkdr.weatherapp.domain.usecase.ForecastWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val currentWeatherUseCase: CurrentWeatherUseCase,
    private val forecastWeatherUseCase: ForecastWeatherUseCase,
) : ViewModel() {

    private val _currentWeather: MutableStateFlow<Resource<CurrentWeatherEntity>> = MutableStateFlow(Resource.Loading)
    val currentWeather: StateFlow<Resource<CurrentWeatherEntity>> get() = _currentWeather.asStateFlow()

    private val _forecastWeather: MutableStateFlow<Resource<ForecastWeatherEntity>> = MutableStateFlow(Resource.Loading)
    val forecastWeather: StateFlow<Resource<ForecastWeatherEntity>> get() = _forecastWeather.asStateFlow()

    private val _date : MutableLiveData<String> = MutableLiveData("")
    val date : LiveData<String> get() = _date

    fun getTodaysCalendar(calendar: Calendar){
            val (day, month, year) = calendar
            val dayFormatted = day.toString().formatCalendar()
            val monthFormatted = month.toString().formatCalendar()
            _date.postValue("$dayFormatted.$monthFormatted.$year")
    }

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

    fun getMappedCurrentWeather(lat: String, long: String) = viewModelScope.launch(Dispatchers.IO) {
        currentWeatherUseCase.invoke(lat, long).collect {
            _currentWeather.emit(it)
        }
    }

    fun getMappedForecastWeather(lat: String, long: String) = viewModelScope.launch(Dispatchers.IO) {
        forecastWeatherUseCase.invoke(lat, long).collect {
            _forecastWeather.emit(it)
        }
    }


}