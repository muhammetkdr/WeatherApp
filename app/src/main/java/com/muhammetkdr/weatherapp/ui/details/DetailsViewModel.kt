package com.muhammetkdr.weatherapp.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.muhammetkdr.weatherapp.common.extensions.EMPTY
import com.muhammetkdr.weatherapp.common.extensions.orZero
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.forecastuidata.DatesAndTimes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor() : ViewModel() {

    private val _humidity: MutableLiveData<String> = MutableLiveData()
    val humidity: LiveData<String>
        get() = _humidity

    private val _pressure: MutableLiveData<String> = MutableLiveData()
    val pressure: LiveData<String>
        get() = _pressure

    private val _grndLevel: MutableLiveData<String> = MutableLiveData()
    val grndLevel: LiveData<String>
        get() = _grndLevel

    private val _barEntry: MutableLiveData<LineDataSet> = MutableLiveData()
    val barEntry: LiveData<LineDataSet>
        get() = _barEntry

    fun getData(data: DatesAndTimes) {
        val tempList: MutableList<Float> = mutableListOf()
        val hoursIndexList: MutableList<Float> = mutableListOf()
        val barEntryList: MutableList<Entry> = mutableListOf()
        viewModelScope.launch(Dispatchers.IO) {
            data.childRvUiData.forEachIndexed { index, uiData ->
                hoursIndexList.add(index.toFloat())
                tempList.add(uiData.temperature.toFloat().orZero())
            }
            for (item in hoursIndexList) {
                barEntryList.add(Entry(hoursIndexList[item.toInt()], tempList[item.toInt()]))
            }
            _humidity.postValue(data.humidity)
            _pressure.postValue(data.pressure)
            _grndLevel.postValue(data.grndLevel)

            val lineDataSet = LineDataSet(barEntryList, String.EMPTY)
            _barEntry.postValue(lineDataSet)
        }
    }
}