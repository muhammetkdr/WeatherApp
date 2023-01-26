package com.muhammetkdr.weatherapp.nothing.map.general

class UserRepositoryImpl(
    private val userDataSource: UserDataSource,
    private val dataUserToDomainUserMapper: DataUserToDomainUserMapper,
) : UserRepository {
    override fun getUser(): DomainUser = userDataSource.getUser().let(dataUserToDomainUserMapper::map)
}