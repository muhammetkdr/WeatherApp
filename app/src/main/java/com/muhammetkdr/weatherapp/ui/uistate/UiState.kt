package com.muhammetkdr.weatherapp.ui.uistate

sealed class UiState<out T: Any> {
    object Loading : UiState<Nothing>()
    data class Error(val error: String) : UiState<Nothing>()
    data class Success<out T: Any>(val data:T) : UiState<T>()
}