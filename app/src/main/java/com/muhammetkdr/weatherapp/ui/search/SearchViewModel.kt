package com.muhammetkdr.weatherapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.domain.entity.cities.CitiesEntity
import com.muhammetkdr.weatherapp.domain.usecase.CitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val citiesUseCase: CitiesUseCase) :
    ViewModel() {

    private val _cityList: MutableStateFlow<Resource<List<CitiesEntity>>> =
        MutableStateFlow(Resource.Loading)
    val cityList: StateFlow<Resource<List<CitiesEntity>>>
        get() = _cityList

    private val _cities: MutableLiveData<List<CitiesEntity>> = MutableLiveData(emptyList())
    val cities: LiveData<List<CitiesEntity>>
        get() = _cities

    private val _citiesQueryList: MutableLiveData<List<CitiesEntity>> = MutableLiveData(emptyList())
    val citiesQueryList: LiveData<List<CitiesEntity>>
        get() = _citiesQueryList

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            citiesUseCase.invoke().collect {
                _cityList.emit(it)
            }
        }
    }

    fun setCityListData(list: List<CitiesEntity>) {
        viewModelScope.launch(Dispatchers.IO) {
            _cities.postValue(list)
        }
    }

    fun filterCityQuery(query: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (query == null) return@launch

            val queryList = mutableListOf<CitiesEntity>()
            var citiesList = listOf<CitiesEntity>()

            _cities.value?.let {
                citiesList = it
            }

            if (query.isEmpty()) {
                _citiesQueryList.postValue(citiesList)
                return@launch
            }

            _cities.value?.forEach {
                if (it.cityName.contains(query, true)) {
                    queryList.add(it)
                }
            }
            _citiesQueryList.postValue(queryList)
        }
    }

}