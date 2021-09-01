package com.example.weatherapp.injection.module

import com.example.data.WeatherRepo
import com.example.usecases.weather.*
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideGetCityInfoUseCase(weatherRepo: WeatherRepo): GetCityInfoUseCase {
        return GetCityInfoUseCase(weatherRepo)
    }

    @Provides
    fun provideSaveCityInfoUseCase(weatherRepo: WeatherRepo): SaveCityInfoUseCase {
        return SaveCityInfoUseCase(weatherRepo)
    }

    @Provides
    fun provideUpdateCityInfoUseCase(weatherRepo: WeatherRepo): UpdateCityInfoUseCase {
        return UpdateCityInfoUseCase(weatherRepo)
    }

    @Provides
    fun provideRemoveCityUseCase(weatherRepo: WeatherRepo): RemoveCityUseCase {
        return RemoveCityUseCase(weatherRepo)
    }

    @Provides
    fun provideGetCityListUseCase(weatherRepo: WeatherRepo): GetCityListUseCase {
        return GetCityListUseCase(weatherRepo)
    }

    @Provides
    fun provideGetForecastUseCase(weatherRepo: WeatherRepo): GetForecastUseCase {
        return GetForecastUseCase(weatherRepo)
    }

    @Provides
    fun provideGetForecastListUseCase(weatherRepo: WeatherRepo): GetForecastListUseCase {
        return GetForecastListUseCase(weatherRepo)
    }

    @Provides
    fun provideSaveForecastListUseCase(weatherRepo: WeatherRepo): SaveForecastUseCase {
        return SaveForecastUseCase(weatherRepo)
    }
}