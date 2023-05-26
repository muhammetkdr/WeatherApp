package com.muhammetkdr.weatherapp.ui.search

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.data.listmapper.ListMapper
import com.muhammetkdr.weatherapp.domain.entity.cities.CitiesEntity
import com.muhammetkdr.weatherapp.domain.usecase.CitiesUseCase
import com.muhammetkdr.weatherapp.ui.uistate.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val citiesUseCase: CitiesUseCase,
    private val searchListMapper: ListMapper<CitiesEntity, SearchUiData>
) : ViewModel() {

    private val _cityList: MutableStateFlow<UiState<List<SearchUiData>>> =
        MutableStateFlow(UiState.Loading)
    val cityList: StateFlow<UiState<List<SearchUiData>>>
        get() = _cityList

    private val _cities: MutableLiveData<List<SearchUiData>> = MutableLiveData(emptyList())
    val cities: LiveData<List<SearchUiData>>
        get() = _cities

    private val _citiesQueryList: MutableLiveData<List<SearchUiData>> = MutableLiveData(emptyList())
    val citiesQueryList: LiveData<List<SearchUiData>>
        get() = _citiesQueryList

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            citiesUseCase.invoke().collect {
                searchUiDataMapperHandler(it)
            }
        }
    }

    private fun searchUiDataMapperHandler(citiesEntityData: Resource<List<CitiesEntity>>) {
        viewModelScope.launch(Dispatchers.IO) {
            when (citiesEntityData) {
                is Resource.Error -> {
                    _cityList.emit(UiState.Error(citiesEntityData.error))
                }

                is Resource.Loading -> _cityList.emit(UiState.Loading)
                is Resource.Success -> {
                    _cityList.emit(UiState.Success(searchListMapper.map(citiesEntityData.data)))
                }
            }
        }
    }

    fun setCityListData(list: List<SearchUiData>) {
        viewModelScope.launch(Dispatchers.IO) {
            _cities.postValue(list)
        }
    }

    fun filterCityQuery(query: Editable?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (query == null) return@launch

            val queryList = mutableListOf<SearchUiData>()
            var citiesList = listOf<SearchUiData>()

            _cities.value?.let {
                citiesList = it
            }

            if (query.isEmpty()) {
                _citiesQueryList.postValue(citiesList)
                return@launch
            }

            _cities.value?.forEach {
                if (it.cityName.contains(query.toString(), true)) {
                    queryList.add(it)
                }
            }
            _citiesQueryList.postValue(queryList)
        }
    }

}