package com.example.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.Forecast

@Entity
data class ForecastEntity(
    @PrimaryKey
    val id: String,
    val cityId: Int,
    val date: String,
    val temperature: Double,
    val humidity: Double,
    val rainChances: Double,
    val windSpeed: Double
)

fun ForecastEntity.toDomain(): Forecast {
    return Forecast(
        id = this.id,
        cityId = this.cityId,
        date = this.date,
        humidity = this.humidity,
        windSpeed = this.windSpeed,
        temperature = this.temperature,
        rainChances = this.rainChances
    )
}
