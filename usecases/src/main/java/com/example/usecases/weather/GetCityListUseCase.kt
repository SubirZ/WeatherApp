package com.example.usecases.weather

import com.example.data.WeatherRepo
import com.example.domain.City
import com.example.usecases.BaseUseCase
import com.example.usecases.weather.SaveCityInfoUseCase.Params

class GetCityListUseCase(private val weatherRepo: WeatherRepo) :
    BaseUseCase<List<City>, Params> {

    override suspend fun perform(): List<City> {
        return weatherRepo.getCityList()
    }
}