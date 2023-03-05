package com.muhammetkdr.weatherapp.common.extensions

import java.text.SimpleDateFormat
import java.util.*

fun String.capitalizeFirstWord(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase())
            it.titlecase(Locale.getDefault())
        else it.toString()
    }
}

fun String.capitalizeWords(): String = split(" ").map { word ->
    word.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.ROOT
        ) else it.toString()
    }
}.joinToString(" ")

fun String.formatCallendar(): String {
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
    val MM = month
    val yy = year % 100
    val YY = year / 100
    // Calculating the day
    var calculate = day + 13 * (MM + 1) / 5 + yy + yy / 4 + YY / 4 + 5 * YY
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

fun String?.hideNameAndSurname(): String? {
    if (this.isNullOrEmpty()) return this
    val nameAndSurname = " $this"

    nameAndSurname.trimEnd(' ')

    val firstCharacterIndexes = mutableListOf<Int>()
    nameAndSurname.forEachIndexed { index, char ->
        if (char == ' ') {
            firstCharacterIndexes.add(index)
        }
    }
    var newName = ""
    firstCharacterIndexes.forEachIndexed { index, i ->
        newName += if (firstCharacterIndexes.lastIndex == index) {
            nameAndSurname[i + 1].toString().padEnd(nameAndSurname.length - i - 1, '*')
        } else {
            nameAndSurname[i + 1].toString()
                .padEnd(firstCharacterIndexes[index + 1] - i - 1, '*') + ' '
        }
    }
    return newName
}

val String.Companion.EMPTY: String get() = ""