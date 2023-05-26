package com.muhammetkdr.weatherapp.ui.uistate

import androidx.annotation.StringRes

sealed class UiState<out T: Any> {
    object Loading : UiState<Nothing>()
    data class Error(@StringRes val error: Int) : UiState<Nothing>()
    data class Success<out T: Any>(val data:T) : UiState<T>()
}