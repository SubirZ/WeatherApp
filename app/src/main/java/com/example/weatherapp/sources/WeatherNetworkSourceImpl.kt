package com.example.weatherapp.sources

import com.example.data.WeatherNetworkSource
import com.example.data.remote.WeatherApis
import com.example.domain.CityResponse
import com.example.domain.ForecastResponse
import com.example.domain.SafeResult

class WeatherNetworkSourceImpl(
    private val weatherApis: WeatherApis
) : WeatherNetworkSource {

    override suspend fun getCityWeather(lat: Double, lng: Double): SafeResult<CityResponse> {
        val response = weatherApis.getCityWeather(lat = lat.toString(), lng = lng.toString())
        if(response.isSuccessful) {
            val data = response.body()
            if (data != null) {
                return SafeResult.Success(data)
            }
        }
        return SafeResult.Failure(response)
    }

    override suspend fun getForecast(lat: Double, lng: Double): SafeResult<ForecastResponse> {
        val response = weatherApis.getForecast(lat = lat.toString(), lng = lng.toString())
        if(response.isSuccessful) {
            val data = response.body()
            if (data != null) {
                return SafeResult.Success(data)
            }
        }
        return SafeResult.Failure(response)
    }
}