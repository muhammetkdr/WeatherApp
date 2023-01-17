package com.muhammetkdr.weatherapp.common.utils

sealed class Resource<out T : Any> {
    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Error<out T : Any>(val error: String) : Resource<T>()
    object Loading : Resource<Nothing>()
}
