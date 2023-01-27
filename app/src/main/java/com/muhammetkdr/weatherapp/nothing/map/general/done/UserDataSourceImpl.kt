package com.muhammetkdr.weatherapp.nothing.map.general.done

class UserDataSourceImpl(
    private val appDatabaseapisvce: AppDatabaseapisvce,
) : UserDataSource {
    override fun getUser(): DataUser =
        appDatabaseapisvce.getUser()
}