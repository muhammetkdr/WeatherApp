package com.muhammetkdr.weatherapp.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.muhammetkdr.weatherapp.common.extensions.EMPTY
import com.muhammetkdr.weatherapp.common.extensions.orZero
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.forecastuidata.DatesAndTimes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class WeatherConditionState(
    val humidity: String = String.EMPTY,
    val pressure: String = String.EMPTY,
    val grndLevel: String = String.EMPTY
)

@HiltViewModel
class DetailsViewModel @Inject constructor() : ViewModel() {

    private val _weatherConditionStates = MutableStateFlow(WeatherConditionState())
    val weatherConditionStates: StateFlow<WeatherConditionState> get() = _weatherConditionStates

    private val _barEntry: MutableStateFlow<LineDataSet?> = MutableStateFlow(null)
    val barEntry: StateFlow<LineDataSet?>
        get() = _barEntry

    fun getDatesAndTimesData(data: DatesAndTimes) {
        val tempList: MutableList<Float> = mutableListOf()
        val hoursIndexList: MutableList<Float> = mutableListOf()
        val barEntryList: MutableList<Entry> = mutableListOf()
        viewModelScope.launch{
            data.childRvUiData.forEachIndexed { index, uiData ->
                hoursIndexList.add(index.toFloat())
                tempList.add(uiData.temperature.toFloat().orZero())
            }
            for (item in hoursIndexList) {
                barEntryList.add(Entry(hoursIndexList[item.toInt()], tempList[item.toInt()]))
            }

            _weatherConditionStates.update {
                it.copy(
                    humidity = data.humidity,
                    pressure = data.pressure,
                    grndLevel = data.grndLevel
                )
            }

            val lineDataSet = LineDataSet(barEntryList, String.EMPTY)
            _barEntry.update { lineDataSet }
        }
    }
}