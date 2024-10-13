package com.muhammetkdr.weatherapp.data.common.extensions

import com.muhammetkdr.weatherapp.common.extensions.capitalizeWords
import junit.framework.TestCase.assertEquals
import org.junit.Test

class StringCapitalizeWordsTest {

    @Test
    fun `capitalizeWords capitalizes the first letter of each word in a string`() {
        // Given
        val input = "hello world"
        val expectedOutput = "Hello World"


        // When
        val result = input.capitalizeWords()

        // Then
        assertEquals(result, expectedOutput)
    }

    @Test
    fun `capitalizeWords leaves the string unchanged if it is already capitalized`() {
        // Given
        val input = "Hello World"
        val expectedOutput = "Hello World"

        // When
        val result = input.capitalizeWords()

        // Then
        assertEquals(expectedOutput, result)
    }

    @Test
    fun `capitalizeWords capitalizes the first letter of each word and preserves the rest of the string`() {
        // Given
        val input = "heLlo woRld"
        val expectedOutput = "HeLlo WoRld"

        // When
        val result = input.capitalizeWords()

        // Then
        assertEquals(expectedOutput, result)
    }

    @Test
    fun `capitalizeWords returns an empty string if the input string is empty`() {
        // Given
        val input = ""
        val expectedOutput = ""

        // When
        val result = input.capitalizeWords()

        // Then
        assertEquals(expectedOutput, result)
    }
}