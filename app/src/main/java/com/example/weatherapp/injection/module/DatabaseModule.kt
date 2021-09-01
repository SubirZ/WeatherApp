package com.example.weatherapp.injection.module

import android.content.Context
import androidx.room.Room
import com.example.data.local.WeatherAppDB
import com.example.weatherapp.injection.qualifiers.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideWeatherAppDB(@ApplicationContext context: Context): WeatherAppDB {
        return Room.databaseBuilder(context, WeatherAppDB::class.java, WeatherAppDB.DB_NAME).build()
    }
}