package com.muhammetkdr.weatherapp.common.logger

import javax.inject.Inject

class LoggerImpl @Inject constructor(): Logger {

    override fun log(message: String?) {
        println("Logger: $message")
    }

}