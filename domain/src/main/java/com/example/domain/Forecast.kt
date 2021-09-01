package com.example.domain

data class Forecast(
    val id: String,
    val cityId: Int,
    val date: String,
    val temperature: Double,
    val humidity: Double,
    val rainChances: Double,
    val windSpeed: Double
)
