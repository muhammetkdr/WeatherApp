package com.muhammetkdr.weatherapp.common.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.showSnackbar(msg: String) {
    Snackbar.make(this, msg, 10000).show()
}