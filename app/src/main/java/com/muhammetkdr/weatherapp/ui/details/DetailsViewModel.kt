package com.muhammetkdr.weatherapp.ui.details

import androidx.lifecycle.*
import com.github.mikephil.charting.data.Entry
import com.muhammetkdr.weatherapp.common.extensions.orZero
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.forecastuidata.DatesAndTimes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor() : ViewModel() {

    private val tempList: MutableList<Float> = mutableListOf()
    private val hoursIndexList: MutableList<Float> = mutableListOf()
    private val barEntryList: MutableList<Entry> = mutableListOf()

    private var _hoursList: List<String> = mutableListOf()
    val hoursList: List<String>
        get() = _hoursList

    private val _barEntry = MutableLiveData<List<Entry>>()
    val barEntry: LiveData<List<Entry>>
        get() = _barEntry

    private val _humidity: MutableLiveData<String> = MutableLiveData()
    val humidity: LiveData<String>
        get() = _humidity

    private val _pressure: MutableLiveData<String> = MutableLiveData()
    val pressure: LiveData<String>
        get() = _pressure

    private val _grndLevel: MutableLiveData<String> = MutableLiveData()
    val grndLevel: LiveData<String>
        get() = _grndLevel

    fun getData(data: DatesAndTimes) {
        viewModelScope.launch(Dispatchers.IO) {
            data.childRvUiData.forEachIndexed { index, uiData ->
                hoursIndexList.add(index.toFloat())
                tempList.add(uiData.temperature.toFloat().orZero())
            }
            for (item in hoursIndexList) {
                barEntryList.add(Entry(hoursIndexList[item.toInt()], tempList[item.toInt()]))
            }
            _hoursList = data.hours

            _barEntry.postValue(barEntryList)

            _humidity.postValue(data.humidity)
            _pressure.postValue(data.pressure)
            _grndLevel.postValue(data.grndLevel)
        }
    }
}