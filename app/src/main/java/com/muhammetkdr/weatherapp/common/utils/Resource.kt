package com.muhammetkdr.weatherapp.common.utils

sealed class Resource<out T : Any> {
    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Error<out T: Any>(val message: String) : Resource<T>()
    object Loading : Resource<Nothing>()
}
