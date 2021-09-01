package com.example.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.City

@Entity
data class CityEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,
    val name: String,
    val lat: Double,
    val lng: Double,
    val lastTemperature: Double?,
    val minTemperature: Double?,
    val maxTemperature: Double?,
    val weatherImage: String?,
    val weatherCondition: String?,
    val realFeel: Double?,
    val humidity: Double?,
    val windSpeed: Double?,
    val pressure: Double?,
    val rainChances: Double?
)

fun CityEntity.toDomain(): City {
    return City(
        id = this.id,
        cityName = this.name,
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
