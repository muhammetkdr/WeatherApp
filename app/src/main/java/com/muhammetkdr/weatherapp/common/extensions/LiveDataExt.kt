package com.muhammetkdr.weatherapp.common.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

inline fun <T> LiveData<T>.observeIfNotNull(owner: LifecycleOwner, crossinline observer: (T) -> Unit) {
    this.observe(owner) { data ->
        if (data != null) {
            observer(data)
        }
    }
}