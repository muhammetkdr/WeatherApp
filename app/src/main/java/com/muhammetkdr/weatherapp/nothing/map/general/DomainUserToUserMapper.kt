package com.muhammetkdr.weatherapp.nothing.map.general

import com.muhammetkdr.weatherapp.nothing.map.general.done.DomainUser
import com.muhammetkdr.weatherapp.nothing.map.general.done.Mapper

class DomainUserToUserMapper : Mapper<DomainUser, User> {
    override fun map(input: DomainUser): User = with(input) {
        User(id,fullName)
    }
}