package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.WeatherAppDB.Companion.DB_VERSION
import com.example.data.local.dao.CityDao
import com.example.data.local.dao.ForecastDao
import com.example.data.local.entities.CityEntity
import com.example.data.local.entities.ForecastEntity

@Database(
    entities = [CityEntity::class, ForecastEntity::class],
    version = DB_VERSION
)

abstract class WeatherAppDB : RoomDatabase() {

    abstract fun cityDao(): CityDao
    abstract fun forecastDao(): ForecastDao

    companion object {
        const val DB_NAME = "weather_app_db"
        const val DB_VERSION = 1
    }
}