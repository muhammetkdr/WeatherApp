package com.muhammetkdr.weatherapp.data.api.city

import com.muhammetkdr.weatherapp.data.dto.city.CityResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface CityApi {
    @GET("mskdr/bcea4af3761537dbc81fa38812332a9e/raw/887716bdb0891bc5c1c8d1e93cdc9e5f818fa147/cities_of_turkey")
    suspend fun getCityResponse(): Response<List<CityResponseItem>>
}