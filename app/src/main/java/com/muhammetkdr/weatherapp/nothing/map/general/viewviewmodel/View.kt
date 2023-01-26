package com.muhammetkdr.weatherapp.nothing.map.general.viewviewmodel

// Presentation
class View(
    private val userViewModel: UserViewModel
) {
    fun displayUserData() {
        val user = userViewModel.user
        println(user)
    }
}