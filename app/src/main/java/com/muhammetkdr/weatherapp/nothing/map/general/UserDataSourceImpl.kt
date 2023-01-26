package com.muhammetkdr.weatherapp.nothing.map.general

class UserDataSourceImpl(
    private val appDatabase: AppDatabase,
) : UserDataSource {
    override fun getUser(): DataUser =
        appDatabase.getUser()
}