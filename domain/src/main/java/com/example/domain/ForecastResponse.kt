package com.example.domain

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastResponse(
    val timezone: String,
    val timezone_offset: Int,
    val daily: List<Daily>
)

@JsonClass(generateAdapter = true)
data class Daily (
	val dt : Long,
	val temp : Temp,
	val humidity : Double,
	val wind_speed : Double,
	val clouds : Double
)

@JsonClass(generateAdapter = true)
data class Temp (
	val day : Double
)