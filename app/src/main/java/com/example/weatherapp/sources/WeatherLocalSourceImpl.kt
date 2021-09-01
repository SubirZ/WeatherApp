package com.example.weatherapp.sources

import com.example.data.WeatherLocalSource
import com.example.data.local.WeatherAppDB
import com.example.data.local.entities.CityEntity
import com.example.data.local.entities.ForecastEntity
import com.example.data.local.entities.toDomain
import com.example.domain.City
import com.example.domain.Forecast
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class WeatherLocalSourceImpl(
    private val db: WeatherAppDB,
    private val executionContext: CoroutineContext
) :
    WeatherLocalSource {

    override suspend fun saveCityInfo(cityInfo: City): Long {
        return withContext(executionContext) {
            return@withContext db.cityDao().insert(cityInfo.toEntity())
        }
    }

    override suspend fun updateCityInfo(cityInfo: City) {
        withContext(executionContext) {
            db.cityDao().update(cityInfo.toEntity())
        }
    }

    override suspend fun removeCity(cityId: Int) {
        withContext(executionContext) {
            db.cityDao().deleteCityById(cityId)
            db.forecastDao().deleteForecastsByCityId(cityId)
        }
    }

    override suspend fun getCityList(): List<City> {
        return withContext(executionContext) {
            val cityEntityList = db.cityDao().getCityList()
            cityEntityList.map { cityEntity ->
                cityEntity.toDomain()
            }
        }
    }

    override suspend fun getCityInfo(cityId: Int): City {
        return withContext(executionContext) {
            db.cityDao().getCityById(cityId).toDomain()
        }
    }

    override suspend fun getForecastById(cityId: Int): List<Forecast> {
        return withContext(executionContext) {
            val forecastEntityList = db.forecastDao().getForecastListByCityId(cityId)
            forecastEntityList.map { forecastEntity ->
                forecastEntity.toDomain()
            }
        }
    }

    override suspend fun saveForecasts(forecasts: List<Forecast>): Array<Long> {
        return withContext(executionContext) {
            return@withContext db.forecastDao()
                .insertMultiple(forecasts.map {
                    it.toEntity()
                })
        }
    }

    private fun City.toEntity(): CityEntity {
        return CityEntity(
            id = this.id,
            name = this.cityName,
            lat = this.lat,
            lng = this.lng,
            lastTemperature = this.lastTemperature,
            minTemperature = this.minTemperature,
            maxTemperature = this.maxTemperature,
            weatherImage = this.weatherImage,
            weatherCondition = this.weatherCondition,
            realFeel = this.realFeel,
            humidity = this.humidity,
            windSpeed = this.windSpeed,
            pressure = this.pressure,
            rainChances = this.rainChances
        )
    }

    private fun Forecast.toEntity(): ForecastEntity {
        return ForecastEntity(
            id = this.id,
            cityId = this.cityId,
            date = this.date,
            humidity = this.humidity,
            windSpeed = this.windSpeed,
            temperature = this.temperature,
            rainChances = this.rainChances
        )
    }
}
