package com.muhammetkdr.weatherapp.common.extensions

import java.text.SimpleDateFormat
import java.util.*

fun String.capitalizeWords(): String = split(" ").joinToString(" ") { word ->
    word.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.ROOT
        ) else it.toString()
    }
}


fun String.formatCalendar(): String {
    return if (this.length == 1) {
        this.padStart(2, '0')
    } else {
        this
    }
}

fun String.deleteIfThereIsZero():String{
    return if(this.first().toString()=="0")
        this.drop(1)
    else{
        this
    }
}

fun String.zellerCongruence(): String {
    var year = this.substring(0..3).toInt()
    var month = this.substring(5..6).deleteIfThereIsZero().toInt()
    val day = this.substring(8..9).deleteIfThereIsZero().toInt()

    if (month == 1) { // Checking the month if it's "January"
        month = 13
        year--
    }
    if (month == 2) { // Checking the month if it's "February"
        month = 14
        year--
    }
    val mM = month
    val yy = year % 100
    val yY = year / 100
    // Calculating the day
    var calculate = day + 13 * (mM + 1) / 5 + yy + yy / 4 + yY / 4 + 5 * yY
    calculate %= 7 // Finding the day
    return when (calculate) {
        0 ->{
            "Cumartesi"
        }
        1 -> {
            "Pazar"
        }
        2 -> {
            "Pazartesi"
        }
        3 -> {
            "Salı"
        }
        4 -> {
            "Çarşamba"
        }
        5 -> {
            "Perşembe"
        }
        6 -> {
            "Cuma"
        }
        else -> {
            "Black Friday XD"
        }
    }
}

fun String.getDateInAnotherFormat(inputFormat: String, outputFormat: String): String =
    SimpleDateFormat(inputFormat, Locale.getDefault()).parse(this)
        ?.let { SimpleDateFormat(outputFormat, Locale.getDefault()).format(it) } ?: ""

val String.Companion.EMPTY: String by lazy { "" }