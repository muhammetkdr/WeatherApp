package com.muhammetkdr.weatherapp.nothing.map.general.done

import com.muhammetkdr.weatherapp.nothing.map.general.done.DomainUser
import com.muhammetkdr.weatherapp.nothing.map.general.done.GetUserUseCase
import com.muhammetkdr.weatherapp.nothing.map.general.done.UserRepository

class GetUserUseCaseImpl(
    private val userRepository: UserRepository
) : GetUserUseCase {
    override fun invoke(): DomainUser = userRepository.getUser()
}