package com.muhammetkdr.weatherapp.common.extensions


fun Float?.orZero() = this ?: 0.0f

fun Int?.orZero() = this ?: 0

fun Double?.orZero() = this ?: 0.0

fun Double?.nullToString(): String {
    return this?.toString() ?: "null"
}