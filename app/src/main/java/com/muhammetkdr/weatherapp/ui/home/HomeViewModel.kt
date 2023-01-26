package com.muhammetkdr.weatherapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.data.dto.current.WeatherResponse
import com.muhammetkdr.weatherapp.data.dto.forecast.ForecastResponse
import com.muhammetkdr.weatherapp.data.remote.RemoteDataSourceImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val weatherRepository: RemoteDataSourceImpl): ViewModel() {

    private val _currentWeather: MutableStateFlow<Resource<WeatherResponse>> = MutableStateFlow(Resource.Loading)
    val currentWeather: StateFlow<Resource<WeatherResponse>> get() = _currentWeather.asStateFlow()

    private val _forecastWeatherList: MutableStateFlow<Resource<ForecastResponse>> = MutableStateFlow(Resource.Loading)
    val forecastWeatherList: StateFlow<Resource<ForecastResponse>> get() = _forecastWeatherList.asStateFlow()

//    private val _locationPermitData: MutableLiveData<Boolean> = MutableLiveData(false)
//    val locationPermitData: LiveData<Boolean> get() = _locationPermitData
//
//    fun setLocationData(data:Boolean)= viewModelScope.launch{
//        _locationPermitData.value=data
//    }

    fun getCurrentWeather(lat:Double,long:Double) = viewModelScope.launch(Dispatchers.IO) {
        val latitude = lat.toString()
        val longitude = long.toString()
        _currentWeather.emit(weatherRepository.getCurrentWeather(latitude,longitude))
    }

    fun getForecastWeather(lat:Double,long:Double) = viewModelScope.launch(Dispatchers.IO) {
        val latitude = lat.toString()
        val longitude = long.toString()
        _forecastWeatherList.emit(weatherRepository.getForecastWeather(latitude,longitude))
    }
}