package com.example.usecases.weather

import com.example.data.WeatherRepo
import com.example.domain.Forecast
import com.example.domain.ForecastResponse
import com.example.domain.SafeResult
import com.example.usecases.BaseUseCase
import com.example.usecases.extension.convertLongToDate
import com.example.usecases.weather.GetForecastUseCase.*

class GetForecastUseCase(private val weatherRepo: WeatherRepo) :
    BaseUseCase<ArrayList<Forecast>?, Params> {

    data class Params(
        val cityId: Int,
        val lat: Double,
        val lng: Double
    )

    override suspend fun perform(param: Params): ArrayList<Forecast>? {
        return when (val response = weatherRepo.getForecast(param.lat, param.lng)) {
            is SafeResult.Success -> {
                response.data?.let { forecastResponse ->
                    generateForecast(param.cityId, forecastResponse)
                }
            }
            is SafeResult.Failure -> null
        }
    }

    private fun generateForecast(cityId: Int, forecastResponse: ForecastResponse): ArrayList<Forecast> {
        val forecastList = ArrayList<Forecast>()
        forecastResponse.daily.forEachIndexed { index, daily ->
            forecastList.add(
                Forecast(
                    id = cityId.toString().plus("_$index"),
                    cityId = cityId,
                    date = daily.dt.convertLongToDate(),
                    temperature = daily.temp.day,
                    humidity = daily.humidity,
                    rainChances = daily.clouds,
                    windSpeed = daily.wind_speed
                )
            )
        }
        return forecastList
    }
}