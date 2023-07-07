package com.muhammetkdr.weatherapp.di

import com.muhammetkdr.weatherapp.common.utils.Constants.CITY_BASE_URL
import com.muhammetkdr.weatherapp.common.utils.Constants.WEATHER_BASE_URL
import com.muhammetkdr.weatherapp.data.api.city.CityApi
import com.muhammetkdr.weatherapp.data.api.weather.WeatherAPIService
import com.muhammetkdr.weatherapp.utils.interceptors.NetworkStatusInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpLoggerInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (androidx.viewbinding.BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        return httpLoggingInterceptor
    }

    @Singleton
    @Provides
    fun provideHttpClint(
//        @ApplicationContext context: Context,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        networkStatusInterceptor: NetworkStatusInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS)
//            .addInterceptor(ChuckerInterceptor.Builder(context).build())
            .addInterceptor(networkStatusInterceptor)
            .addInterceptor{ chain ->
                val url = chain
                    .request()
                    .url
                    .newBuilder()
                    .addQueryParameter("appid", com.muhammetkdr.weatherapp.BuildConfig.API_KEY)
                    .build()
                chain.proceed(chain.request().newBuilder().url(url).build())
            }
            .connectTimeout(60, TimeUnit.SECONDS).addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient,
        baseUrl: String,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient)
            .addConverterFactory(gsonConverterFactory).build()
    }

    @Singleton
    @Provides
    fun provideWeatherApiService(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): WeatherAPIService =
        provideRetrofitInstance(
            baseUrl = WEATHER_BASE_URL,
            okHttpClient = okHttpClient,
            gsonConverterFactory = gsonConverterFactory
        ).create(
            WeatherAPIService::class.java
        )

    @Singleton
    @Provides
    fun provideCityApi(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): CityApi = provideRetrofitInstance(
        baseUrl = CITY_BASE_URL,
        okHttpClient = okHttpClient,
        gsonConverterFactory = gsonConverterFactory
    ).create(CityApi::class.java)

}