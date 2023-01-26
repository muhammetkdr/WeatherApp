package com.muhammetkdr.weatherapp.common.utils


sealed class UiState<out T : Any> {
    data class Success<out T : Any>(val data: T) : UiState<T>()
    data class Error <out T : Any>(val error: String): UiState<T>()
    object Loading : UiState<Nothing>()
}