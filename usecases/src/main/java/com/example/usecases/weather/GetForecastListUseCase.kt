package com.example.usecases.weather

import com.example.data.WeatherRepo
import com.example.domain.Forecast
import com.example.usecases.BaseUseCase
import com.example.usecases.weather.GetForecastListUseCase.Params

class GetForecastListUseCase(private val weatherRepo: WeatherRepo) :
    BaseUseCase<List<Forecast>, Params> {

    data class Params(
        val cityId: Int
    )

    override suspend fun perform(param: Params): List<Forecast> {
        return weatherRepo.getForecastById(param.cityId)
    }
}