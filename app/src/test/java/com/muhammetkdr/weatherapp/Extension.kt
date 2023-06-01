package com.muhammetkdr.weatherapp

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source


/** API isteğine karşılık gelen manuel olarak oluşturduğumuz dosyayı sıraya ekliyoruz.
 *  Api isteği attıktan sonra yanıt olarak elimizdeki bu dosya geliyor.
 *
 * */
fun MockWebServer.enqueueMockResponse(fileName: String) {
    javaClass.classLoader?.let {
        val inputStream = it.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(200)
            .setBody(source.readString(Charsets.UTF_8))
        this.enqueue(mockResponse)
    }
}