package com.muhammetkdr.weatherapp.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.muhammetkdr.weatherapp.common.extensions.EMPTY
import com.muhammetkdr.weatherapp.common.extensions.orZero
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.forecastuidata.DatesAndTimes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor() : ViewModel() {

    private val _humidity: MutableStateFlow<String> = MutableStateFlow("")
    val humidity: StateFlow<String>
        get() = _humidity

    private val _pressure: MutableStateFlow<String> = MutableStateFlow("")
    val pressure: StateFlow<String>
        get() = _pressure

    private val _grndLevel: MutableStateFlow<String> = MutableStateFlow("")
    val grndLevel: StateFlow<String>
        get() = _grndLevel

    private val _barEntry: MutableStateFlow<LineDataSet?> = MutableStateFlow(null)
    val barEntry: StateFlow<LineDataSet?>
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
            _humidity.update { data.humidity }
            _pressure.update{data.pressure}
            _grndLevel.update{data.grndLevel}

            val lineDataSet = LineDataSet(barEntryList, String.EMPTY)
            _barEntry.update{lineDataSet}
        }
    }
}