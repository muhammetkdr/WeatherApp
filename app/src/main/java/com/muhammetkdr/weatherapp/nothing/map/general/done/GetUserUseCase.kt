package com.muhammetkdr.weatherapp.nothing.map.general.done

import com.muhammetkdr.weatherapp.nothing.map.general.done.DomainUser

// Domain
interface GetUserUseCase {
    operator fun invoke(): DomainUser
}