package com.muhammetkdr.weatherapp.nothing.map.general.viewviewmodel

import com.muhammetkdr.weatherapp.nothing.map.general.DomainUserToUserMapper
import com.muhammetkdr.weatherapp.nothing.map.general.done.GetUserUseCase
import com.muhammetkdr.weatherapp.nothing.map.general.User

class UserViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val domainPersonToUserMapper: DomainUserToUserMapper,
) {
    val user: User get() = getUserUseCase().let(domainPersonToUserMapper::map)
}