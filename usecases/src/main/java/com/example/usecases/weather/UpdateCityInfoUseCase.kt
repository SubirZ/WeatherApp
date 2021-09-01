package com.example.usecases.weather

import com.example.data.WeatherRepo
import com.example.domain.City
import com.example.usecases.BaseUseCase
import com.example.usecases.weather.UpdateCityInfoUseCase.Params

class UpdateCityInfoUseCase(private val weatherRepo: WeatherRepo) :
    BaseUseCase<Unit, Params> {

    data class Params(
        val cityInfo: City
    )

    override suspend fun perform(param: Params) {
        weatherRepo.updateCityInfo(param.cityInfo)
    }
}