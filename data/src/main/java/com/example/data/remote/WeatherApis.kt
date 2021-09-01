package com.example.data.remote

import com.example.domain.CityResponse
import com.example.domain.ForecastResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApis {

    companion object {
        lateinit var apiKey : String

        fun createRetrofitService(retrofit: Retrofit, apiKey: String): WeatherApis {
            this.apiKey = apiKey
            return retrofit.create(WeatherApis::class.java)
        }
    }

    @GET("weather")
    suspend fun getCityWeather(
        @Query("lat") lat: String,
        @Query("lon") lng: String,
        @Query("appid") ApiKey: String = apiKey
    ): Response<CityResponse>

    @GET("onecall")
    suspend fun getForecast(
        @Query("lat") lat: String,
        @Query("lon") lng: String,
        @Query("exclude") exclude: String = "current,minutely,hourly,alerts",
        @Query("appid") ApiKey: String = apiKey
    ): Response<ForecastResponse>
}