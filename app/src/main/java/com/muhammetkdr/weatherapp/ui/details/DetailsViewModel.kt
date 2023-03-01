package com.muhammetkdr.weatherapp.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.DatesAndTimes
import javax.inject.Inject

class DetailsViewModel @Inject constructor( savedStateHandle: SavedStateHandle) : ViewModel() {

    val mealInCategory: DatesAndTimes? = savedStateHandle.get<DatesAndTimes>("datesAndTimes")
}