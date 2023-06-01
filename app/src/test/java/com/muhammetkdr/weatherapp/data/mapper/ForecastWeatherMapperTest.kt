package com.muhammetkdr.weatherapp.data.mapper

import com.google.common.truth.Truth.assertThat
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.ForecastWeatherEntity
import com.muhammetkdr.weatherapp.testForecastWeatherDto
import org.junit.Before
import org.junit.Test

class ForecastWeatherMapperTest {

    private lateinit var forecastWeatherMapper: ForecastWeatherMapperImpl
    private lateinit var forecastWeatherEntity: ForecastWeatherEntity

    @Before
    fun setup(){
        forecastWeatherMapper = ForecastWeatherMapperImpl()
        forecastWeatherEntity = forecastWeatherMapper.map(testForecastWeatherDto)
    }

    @Test
    fun `when city is mapped`(){
        assertThat(forecastWeatherEntity.city).isEqualTo(testForecastWeatherDto.city)
    }

    @Test
    fun `when main is mapped`(){
        assertThat(forecastWeatherEntity.main).isEqualTo(testForecastWeatherDto.list?.first()?.main)
    }
    @Test
    fun `when list is mapped`(){
        assertThat(forecastWeatherEntity.list).isEqualTo(testForecastWeatherDto.list)
    }
    @Test
    fun `when dtTxt is mapped`(){
        assertThat(forecastWeatherEntity.dtTxt).isEqualTo(testForecastWeatherDto.list?.first()?.dtTxt)
    }

}