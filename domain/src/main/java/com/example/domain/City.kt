package com.example.domain

data class City(
    val id: Int? = null,
    val cityName: String,
    val lat: Double,
    val lng: Double,
    val lastTemperature: Double? = null,
    val minTemperature: Double? = null,
    val maxTemperature: Double? = null,
    val weatherImage: String? = null,
    val weatherCondition: String? = null,
    val realFeel: Double? = null,
    val humidity: Double? = null,
    val windSpeed: Double? = null,
    val pressure: Double? = null,
    val rainChances: Double? = null
)