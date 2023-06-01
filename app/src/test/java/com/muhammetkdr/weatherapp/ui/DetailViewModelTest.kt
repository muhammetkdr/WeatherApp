package com.muhammetkdr.weatherapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.muhammetkdr.weatherapp.testDatesAndTimesData
import com.muhammetkdr.weatherapp.ui.details.DetailsViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class DetailViewModelTest {

    @get:Rule
<<<<<<< HEAD
    val instantTaskExecutorRule = InstantTaskExecutorRule()  // for using liveData test
=======
    val instantTaskExecutorRule = InstantTaskExecutorRule()
>>>>>>> origin/develop

    @Mock
    private lateinit var humidityObserver: Observer<String>

    @Mock
    private lateinit var pressureObserver: Observer<String>

    @Mock
    private lateinit var grndLevelObserver: Observer<String>

    private lateinit var viewModel: DetailsViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = DetailsViewModel()
        viewModel.humidity.observeForever(humidityObserver)
        viewModel.pressure.observeForever(pressureObserver)
        viewModel.grndLevel.observeForever(grndLevelObserver)
    }

    @Test
    fun `getData should update humidity, pressure, grndLevel LiveData`() = runBlocking {
        // Given
        val data = testDatesAndTimesData

        // When
        viewModel.getData(data)

        // Then
        verify(humidityObserver).onChanged(data.humidity)
        assertEquals(data.humidity, viewModel.humidity.value)

        verify(pressureObserver).onChanged(data.pressure)
        assertEquals(data.pressure, viewModel.pressure.value)

        verify(grndLevelObserver).onChanged(data.grndLevel)
        assertEquals(data.grndLevel, viewModel.grndLevel.value)
    }

    @After
    fun tearDown(){
        viewModel.humidity.removeObserver(humidityObserver)
        viewModel.pressure.removeObserver(pressureObserver)
        viewModel.grndLevel.removeObserver(grndLevelObserver)
    }
}