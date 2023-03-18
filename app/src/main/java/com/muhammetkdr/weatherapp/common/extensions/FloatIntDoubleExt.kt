package com.muhammetkdr.weatherapp.common.extensions


fun Float?.orZero() = this ?: 0.0f

fun Double?.nullToString(): String {
    return this?.toString() ?: "null"
}