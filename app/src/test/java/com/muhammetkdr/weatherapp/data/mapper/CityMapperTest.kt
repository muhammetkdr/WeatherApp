package com.muhammetkdr.weatherapp.data.mapper

import com.google.common.truth.Truth.assertThat
import com.muhammetkdr.weatherapp.data.listmapper.CitiesListMapperImpl
import com.muhammetkdr.weatherapp.testCityList
import org.junit.Before
import org.junit.Test

class CityMapperTest {
    private lateinit var cityListMapper: CitiesListMapperImpl

    @Before
    fun setup() {
        cityListMapper = CitiesListMapperImpl()
    }

    @Test
    fun `when cityName is mapped`(){
        val cityName = testCityList.first().name
        val mappedCityname = cityListMapper.map(testCityList).first().cityName
        assertThat(cityName).isEqualTo(mappedCityname)
    }

    @Test
    fun `when latitude is mapped`(){
        val latitude = testCityList.first().latitude
        val mappedlatitude = cityListMapper.map(testCityList).first().latitude
        assertThat(latitude).isEqualTo(mappedlatitude)
    }

    @Test
    fun `when longitude is mapped`(){
        val longitude = testCityList.first().longitude
        val mappedLongitude = cityListMapper.map(testCityList).first().longitude
        assertThat(longitude).isEqualTo(mappedLongitude)
    }

    @Test
    fun `when plateCode is mapped`(){
        val plateCode = testCityList.first().id.toString()
        val mappedPlateCode = cityListMapper.map(testCityList).first().cityPlateCode
        assertThat(plateCode).isEqualTo(mappedPlateCode)
    }
}