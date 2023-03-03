package com.muhammetkdr.weatherapp.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.DatesAndTimes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor( savedStateHandle: SavedStateHandle) : ViewModel() {

    val mealInCategory: DatesAndTimes? = savedStateHandle.get<DatesAndTimes>("datesAndTimes")
}