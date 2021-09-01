package com.example.weatherapp.injection.module

import com.example.data.WeatherLocalSource
import com.example.data.WeatherNetworkSource
import com.example.data.WeatherRepo
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideWeatherRepo(
        weatherNetworkSource: WeatherNetworkSource,
        weatherLocalSource: WeatherLocalSource
    ): WeatherRepo {
        return WeatherRepo(weatherNetworkSource, weatherLocalSource)
    }
}