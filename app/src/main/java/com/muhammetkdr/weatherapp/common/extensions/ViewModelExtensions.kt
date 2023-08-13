package com.muhammetkdr.weatherapp.common.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 *    Created By Muhammet KÜDÜR
 *    17.07.2023
 */

fun <T> ViewModel.collectInViewModelScope(
    flow: Flow<T>,
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    action: suspend (T) -> Unit,
) {
    viewModelScope.launch(dispatcher) {
        flow.collect {
            action(it)
        }
    }
}