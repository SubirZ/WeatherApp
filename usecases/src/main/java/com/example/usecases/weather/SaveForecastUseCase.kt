package com.example.usecases.weather

import com.example.data.WeatherRepo
import com.example.domain.Forecast
import com.example.usecases.BaseUseCase
import com.example.usecases.weather.SaveForecastUseCase.Params

class SaveForecastUseCase(private val weatherRepo: WeatherRepo) :
    BaseUseCase<Array<Long>, Params> {

    data class Params(
        val forecasts: List<Forecast>
    )

    override suspend fun perform(param: Params): Array<Long> {
        return weatherRepo.saveForecasts(param.forecasts)
    }
}