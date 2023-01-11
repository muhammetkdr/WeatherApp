package com.muhammetkdr.weatherapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.model.WeatherResponse
import com.muhammetkdr.weatherapp.repository.WeatherRepository
import com.muhammetkdr.weatherapp.repository.WeatherRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val weatherRepository: WeatherRepositoryImpl): ViewModel() {

    private val _weatherList: MutableLiveData<Resource<WeatherResponse>> = MutableLiveData(Resource.Loading)
    val weatherList: LiveData<Resource<WeatherResponse>> get() = _weatherList

    init {
        getWeather()
    }

    fun getWeather() = viewModelScope.launch {
        _weatherList.postValue(weatherRepository.getCurrentWeather("40","40"))
    }

}