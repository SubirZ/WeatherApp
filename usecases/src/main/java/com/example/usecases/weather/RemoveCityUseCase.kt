package com.example.usecases.weather

import com.example.data.WeatherRepo
import com.example.usecases.BaseUseCase
import com.example.usecases.weather.RemoveCityUseCase.Params

class RemoveCityUseCase(private val weatherRepo: WeatherRepo) :
    BaseUseCase<Unit, Params> {

    data class Params(
        val cityId: Int
    )

    override suspend fun perform(param: Params) {
        weatherRepo.removeCity(param.cityId)
    }
}