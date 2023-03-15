package com.muhammetkdr.weatherapp.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.domain.entity.searchweather.SearchWeatherEntity
import com.muhammetkdr.weatherapp.domain.usecase.SearchWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchWeatherUseCase: SearchWeatherUseCase) :
    ViewModel() {

    private val _currentWeather: MutableStateFlow<Resource<SearchWeatherEntity>> =
        MutableStateFlow(Resource.Loading)
    val currentWeather: StateFlow<Resource<SearchWeatherEntity>> get() = _currentWeather.asStateFlow()

    fun getData(q: String) {
        if (q.isEmpty() || q.count() < 2) {
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            searchWeatherUseCase.invoke(q).collectLatest {
                _currentWeather.emit(it)
            }
        }
    }
}