package com.muhammetkdr.weatherapp.ui

import com.muhammetkdr.weatherapp.testDatesAndTimesData
import com.muhammetkdr.weatherapp.ui.details.DetailsViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DetailsViewModelTest {

    private lateinit var viewModel: DetailsViewModel

    @Before
    fun setup() {
        viewModel = DetailsViewModel()
    }

    @Test
    fun getData_updatesStateFlows() = runBlocking {
        val humidity = "60"
        val pressure = "1013"
        val grndLevel = "1010"

        viewModel.getData(testDatesAndTimesData)

        assertEquals(humidity, viewModel.humidity.first())
        assertEquals(pressure, viewModel.pressure.first())
        assertEquals(grndLevel, viewModel.grndLevel.first())
    }
}