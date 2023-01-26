package com.muhammetkdr.weatherapp.nothing.map.general

class DomainUserToUserMapper : Mapper<DomainUser, User> {
    override fun map(input: DomainUser): User = with(input) {
        User(id, fullName)
    }
}