package com.muhammetkdr.weatherapp.common.extensions

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
} }.joinToString(" ")


fun String.formatCallendar(): String{
    return if(this.length==1){
        this.padStart(2,'0')
    }
    else {
        this
    }
}

fun String?.hideNameAndSurname(): String? {
    if (this.isNullOrEmpty()) return this
    val nameAndSurname = " $this"

    nameAndSurname.trimEnd(' ')

//    while (nameAndSurname.endsWith(' ')) {
//        nameAndSurname.dropLast(1)
//    }

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
