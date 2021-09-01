package com.example.usecases.weather

import com.example.data.WeatherRepo
import com.example.domain.City
import com.example.domain.CityResponse
import com.example.domain.SafeResult
import com.example.usecases.BaseUseCase
import com.example.usecases.extension.safeLet
import com.example.usecases.extension.safeLetMultiple

class GetCityInfoUseCase(private val weatherRepo: WeatherRepo) :
    BaseUseCase<City?, GetCityInfoUseCase.Params> {

    data class Params(
        val cityId: Int? = null,
        val lat: Double,
        val lng: Double
    )

    override suspend fun perform(param: Params): City? {
        return param.cityId?.let { id ->
            weatherRepo.getCityInfo(id)
        } ?: run {
            when (val response = weatherRepo.getCityWeather(param.lat, param.lng)) {
                is SafeResult.Success -> {
                    response.data?.let {
                        generateCityInfo(param.cityId, it)
                    }
                }
                is SafeResult.Failure -> null
            }
        }
    }

    private fun generateCityInfo(cityId: Int?, response: CityResponse): City? {
        var cityInfo: City? = null
        response.apply {
            safeLet(name, weather?.get(0), clouds) { cityName, weather, cloud ->
                safeLet(main, coord, wind) { mainWeather, cord, windCondition ->
                    safeLetMultiple(
                        cord.lat,
                        cord.lon,
                        mainWeather.temp,
                        mainWeather.tempMin,
                        mainWeather.tempMax,
                        weather.icon,
                        weather.description,
                        mainWeather.feelsLike,
                        mainWeather.humidity,
                        windCondition.speed,
                        mainWeather.pressure,
                        cloud.all
                    ) { lat, lng, temp, min, max, icon, weatherType, realFeel, humidity, speed, pressure, rainChances ->
                        cityInfo = City(
                            id = cityId,
                            cityName,
                            lat,
                            lng,
                            temp,
                            min,
                            max,
                            icon,
                            weatherType,
                            realFeel,
                            humidity,
                            speed,
                            pressure,
                            rainChances
                        )
                    }
                }
            }
        }
        return cityInfo
    }
}