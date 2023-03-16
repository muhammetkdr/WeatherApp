package com.muhammetkdr.weatherapp.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.domain.entity.cities.CitiesEntity
import com.muhammetkdr.weatherapp.domain.usecase.CitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val citiesUseCase: CitiesUseCase) :
    ViewModel() {

    private val _cityList: MutableStateFlow<Resource<List<CitiesEntity>>> =
        MutableStateFlow(Resource.Loading)
    val cityList: StateFlow<Resource<List<CitiesEntity>>> get() = _cityList.asStateFlow()

    init {
        getData()
    }

    private fun getData() = viewModelScope.launch(Dispatchers.IO) {
        citiesUseCase.invoke().collect{
            _cityList.emit(it)
        }
    }

//    fun getQueryResult(q: String) {
//        if (q.isEmpty() || q.count() < 2) {
//            return
//        }
//        viewModelScope.launch(Dispatchers.IO) {
//            searchWeatherUseCase.invoke(q).collectLatest {
//                _currentWeather.emit(it)
//            }
//        }
//    }

}