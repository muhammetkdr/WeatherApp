package com.muhammetkdr.weatherapp.nothing.map.general.done

// Domain Model
data class DomainUser(
    val id: String,
    val firstName: String,
    val lastName: String,
) {
    val fullName: String get() = "$firstName $lastName"
}