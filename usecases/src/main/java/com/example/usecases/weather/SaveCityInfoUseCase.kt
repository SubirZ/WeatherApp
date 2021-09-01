package com.example.usecases.weather

import com.example.data.WeatherRepo
import com.example.domain.City
import com.example.usecases.BaseUseCase
import com.example.usecases.weather.SaveCityInfoUseCase.Params

class SaveCityInfoUseCase(private val weatherRepo: WeatherRepo) :
    BaseUseCase<Long, Params> {

    data class Params(
        val cityInfo: City
    )

    override suspend fun perform(param: Params): Long {
        return weatherRepo.saveCityInfo(param.cityInfo)
    }
}