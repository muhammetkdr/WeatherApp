package com.muhammetkdr.weatherapp.ui.search

import androidx.lifecycle.ViewModel
import com.muhammetkdr.weatherapp.domain.usecase.SearchWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchWeatherUseCase: SearchWeatherUseCase): ViewModel() {

}