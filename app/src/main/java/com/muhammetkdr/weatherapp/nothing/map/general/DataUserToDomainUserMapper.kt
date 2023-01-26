package com.muhammetkdr.weatherapp.nothing.map.general

class DataUserToDomainUserMapper : Mapper<DataUser, DomainUser> {
    override fun map(input: DataUser): DomainUser = with(input) {
        DomainUser(use_id, use_firstname, use_lastname)
    }
}