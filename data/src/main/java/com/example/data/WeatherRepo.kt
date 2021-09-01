package com.example.data

import com.example.domain.*

class WeatherRepo(
    private val networkSource: WeatherNetworkSource,
    private val localSource: WeatherLocalSource
) {

    suspend fun getCityWeather(lat: Double, lng: Double): SafeResult<CityResponse> {
        return networkSource.getCityWeather(lat, lng)
    }

    suspend fun saveCityInfo(cityInfo: City): Long {
        return localSource.saveCityInfo(cityInfo)
    }

    suspend fun updateCityInfo(cityInfo: City) {
        localSource.updateCityInfo(cityInfo)
    }

    suspend fun removeCity(cityId: Int) {
        localSource.removeCity(cityId)
    }

    suspend fun getCityList(): List<City> {
        return localSource.getCityList()
    }

    suspend fun getCityInfo(cityId: Int): City {
        return localSource.getCityInfo(cityId)
    }

    suspend fun getForecast(lat: Double, lng: Double): SafeResult<ForecastResponse> {
        return networkSource.getForecast(lat, lng)
    }

    suspend fun getForecastById(cityId: Int): List<Forecast> {
        return localSource.getForecastById(cityId)
    }

    suspend fun saveForecasts(forecasts: List<Forecast> ): Array<Long> {
        return localSource.saveForecasts(forecasts)
    }
}

interface WeatherNetworkSource {
    suspend fun getCityWeather(lat: Double, lng: Double): SafeResult<CityResponse>
    suspend fun getForecast(lat: Double, lng: Double): SafeResult<ForecastResponse>
}

interface WeatherLocalSource {
    suspend fun saveCityInfo(cityInfo: City): Long
    suspend fun updateCityInfo(cityInfo: City)
    suspend fun removeCity(cityId: Int)
    suspend fun getCityList(): List<City>
    suspend fun getCityInfo(cityId: Int): City
    suspend fun getForecastById(cityId: Int): List<Forecast>
    suspend fun saveForecasts(forecasts: List<Forecast>): Array<Long>
}