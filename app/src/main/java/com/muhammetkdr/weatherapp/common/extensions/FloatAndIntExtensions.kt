package com.muhammetkdr.weatherapp.common.extensions


fun Float?.orZero() = this ?: 0.0f

val Int?.orZero: Int get() = this ?: 0