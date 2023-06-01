package com.muhammetkdr.weatherapp.data.mapper

import com.google.common.truth.Truth.assertThat
import com.muhammetkdr.weatherapp.domain.entity.currentweather.CurrentWeatherEntity
import com.muhammetkdr.weatherapp.testCurrentWeatherDto
import org.junit.Before
import org.junit.Test

class CurrentWeatherMapperTest {
    private lateinit var currentWeatherMapper: CurrentWeatherMapperImpl
    private lateinit var currentWeatherEntity: CurrentWeatherEntity

    @Before
    fun setup(){
        currentWeatherMapper = CurrentWeatherMapperImpl()
        currentWeatherEntity = currentWeatherMapper.map(testCurrentWeatherDto)
    }

    @Test
    fun when_weather_is_mapped(){
        assertThat(currentWeatherEntity.weather).isEqualTo(testCurrentWeatherDto.weather)
    }

    @Test
    fun when_name_is_mapped(){
        assertThat(currentWeatherEntity.name).isEqualTo(testCurrentWeatherDto.name)
    }

    @Test
    fun when_main_is_mapped(){
        assertThat(currentWeatherEntity.main).isEqualTo(testCurrentWeatherDto.main)
    }

    @Test
    fun when_sys_is_mapped(){
        assertThat(currentWeatherEntity.sys).isEqualTo(testCurrentWeatherDto.sys)
    }
}