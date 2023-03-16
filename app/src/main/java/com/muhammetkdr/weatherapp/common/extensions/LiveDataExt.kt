package com.muhammetkdr.weatherapp.common.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

fun <T> LiveData<T>.observeIfNotNull(owner: LifecycleOwner, observer: (T) -> Unit) {
    this.observe(owner) { data ->
        if (data != null) {
            observer(data)
        }
    }
}