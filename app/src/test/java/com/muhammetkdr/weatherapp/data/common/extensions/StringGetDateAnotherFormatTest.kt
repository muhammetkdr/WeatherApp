package com.muhammetkdr.weatherapp.data.common.extensions

import com.google.common.truth.Truth.assertThat
import com.muhammetkdr.weatherapp.common.extensions.getDateInAnotherFormat
import junit.framework.TestCase.assertEquals
import org.junit.Test

class StringGetDateAnotherFormatTest {

    @Test
    fun `getDateInAnotherFormat returns the date in the specified output format`() {
        // Given
        val inputDate = "2023-06-01"
        val inputFormat = "yyyy-MM-dd"
        val outputFormat = "dd/MM/yyyy"
        val expectedOutput = "01/06/2023"

        // When
        val result = inputDate.getDateInAnotherFormat(inputFormat, outputFormat)

        // Then
        assertThat(result).isEqualTo(expectedOutput)
    }

    @Test
    fun `getDateInAnotherFormat returns an empty string if the input date is invalid`() {
        // Given
        val inputDate = "invalid-date"
        val inputFormat = "yyyy-MM-dd"
        val outputFormat = "dd/MM/yyyy"
        val expectedOutput = ""

        // When
        val result = inputDate.getDateInAnotherFormat(inputFormat, outputFormat)

        // Then
        assertThat(expectedOutput).isEqualTo(result)
    }

    @Test
    fun `getDateInAnotherFormat returns an empty string if the input format is invalid`() {
        // Given
        val inputDate = "2023-06-01"
        val inputFormat = "format"
        val outputFormat = "dd/MM/yyyy"
        val expectedOutput = ""

        // When
        val result = inputDate.getDateInAnotherFormat(inputFormat, outputFormat)

        // Then
        assertEquals(expectedOutput, result)
    }

    @Test
    fun `getDateInAnotherFormat returns an empty string if the output format is invalid`() {
        // Given
        val inputDate = "2023-06-01"
        val inputFormat = "yyyy-MM-dd"
        val outputFormat = "invalid-format"
        val expectedOutput = ""

        // When
        val result = inputDate.getDateInAnotherFormat(inputFormat, outputFormat)

        // Then
        assertEquals(expectedOutput, result)
    }

}