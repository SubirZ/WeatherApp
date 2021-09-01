package com.example.weatherapp.injection.module

import com.example.data.WeatherLocalSource
import com.example.data.WeatherNetworkSource
import com.example.data.local.WeatherAppDB
import com.example.data.remote.WeatherApis
import com.example.weatherapp.sources.WeatherLocalSourceImpl
import com.example.weatherapp.sources.WeatherNetworkSourceImpl
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers

@Module
class SourceModule {

    @Provides
    internal fun provideWeatherNetworkSource(weatherApis: WeatherApis) : WeatherNetworkSource {
        return WeatherNetworkSourceImpl(weatherApis)
    }

    @Provides
    internal fun provideWeatherLocalSource(db: WeatherAppDB) : WeatherLocalSource {
        return WeatherLocalSourceImpl(db, Dispatchers.IO)
    }
}