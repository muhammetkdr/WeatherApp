package com.muhammetkdr.weatherapp.nothing.map.general

class GetUserUseCaseImpl(
    private val userRepository: UserRepository
) : GetUserUseCase {
    override fun invoke(): DomainUser = userRepository.getUser()
}