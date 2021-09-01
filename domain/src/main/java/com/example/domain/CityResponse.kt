package com.example.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CityResponse(
    val base: String?,
    val clouds: Clouds?,
    val cod: Int?,
    val coord: Coord?,
    val dt: Int?,
    val id: Int?,
    val main: Main?,
    val name: String?,
    val sys: Sys?,
    val timezone: Int?,
    val visibility: Int?,
    val weather: List<Weather>?,
    val wind: Wind?
)

@JsonClass(generateAdapter = true)
data class Clouds(
    val all: Double?
)

@JsonClass(generateAdapter = true)
data class Coord(
    val lat: Double?,
    val lon: Double?
)

@JsonClass(generateAdapter = true)
data class Main(
    @Json(name = "feels_like") val feelsLike: Double?,
    val grndLevel: Int?,
    val humidity: Double?,
    val pressure: Double?,
    val seaLevel: Int?,
    val temp: Double,
    @Json(name = "temp_max") val tempMax: Double?,
    @Json(name = "temp_min") val tempMin: Double?
)

@JsonClass(generateAdapter = true)
data class Sys(
    val id: Int?,
    val country: String?,
    val sunrise: Int?,
    val sunset: Int?,
    val type: Int?
)

@JsonClass(generateAdapter = true)
data class Wind(
    val deg: Int?,
    val gust: Double?,
    val speed: Double?
)

@JsonClass(generateAdapter = true)
data class Weather (
    val id : Int,
    val main : String,
    val description : String,
    val icon : String
)