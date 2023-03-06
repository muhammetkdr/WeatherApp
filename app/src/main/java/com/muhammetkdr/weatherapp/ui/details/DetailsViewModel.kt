package com.muhammetkdr.weatherapp.ui.details

import androidx.lifecycle.*
import com.github.mikephil.charting.data.Entry
import com.muhammetkdr.weatherapp.common.extensions.orZero
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.DatesAndTimes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor( savedStateHandle: SavedStateHandle) : ViewModel() {

    val datesAndTimes: DatesAndTimes? = savedStateHandle.get<DatesAndTimes>("datesAndTimes")

    init {
        getChartData()
    }

    private val tempList : MutableList<Float> = mutableListOf()
    private val hoursIndexList : MutableList<Float> = mutableListOf()
    private val barEntryList : MutableList<Entry> = mutableListOf()

    private var _hoursList : List<String> = mutableListOf()
    val hoursList get() = _hoursList

    private val _barEntry = MutableLiveData<List<Entry>>()
    val barEntry: LiveData<List<Entry>> get() = _barEntry

    private fun getChartData() = viewModelScope.launch(Dispatchers.IO) {
        datesAndTimes?.childRvUiData?.forEachIndexed{ index , uiData ->
            hoursIndexList.add(index.toFloat())
            tempList.add(uiData.temperature.toFloat().orZero())
        }
        for(item in hoursIndexList){
            barEntryList.add(Entry(hoursIndexList[item.toInt()], tempList[item.toInt()]))
        }

        _hoursList = datesAndTimes?.hours ?: emptyList()

        _barEntry.postValue(barEntryList)
    }

}