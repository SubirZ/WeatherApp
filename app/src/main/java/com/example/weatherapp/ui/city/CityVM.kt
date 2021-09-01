package com.example.weatherapp.ui.city

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.City
import com.example.domain.Forecast
import com.example.usecases.weather.*
import com.example.weatherapp.ui.base.BaseVm
import com.example.weatherapp.ui.city.CityVM.CityViewState.*
import com.example.weatherapp.ui.settings.MeasurementUnit
import kotlinx.coroutines.launch
import javax.inject.Inject

class CityVM @Inject constructor(
    private val saveCityInfoUseCase: SaveCityInfoUseCase,
    private val updateCityInfoUseCase: UpdateCityInfoUseCase,
    private val getCityWeatherUseCase: GetCityInfoUseCase,
    private val getForecastUseCase: GetForecastUseCase,
    private val getForecastListUseCase: GetForecastListUseCase,
    private val saveForecastUseCase: SaveForecastUseCase
) : BaseVm() {

    private val _viewState = MutableLiveData<CityViewState>()
    val viewState: LiveData<CityViewState> = _viewState

    var measurementUnit: MeasurementUnit = MeasurementUnit.IMPERIAL

    fun getCityWeather(cityId: String?, lat: Double, lng: Double) {
        cityId?.let {
            silentRefreshCityWeather(cityId.toInt(), lat, lng)
        } ?: run {
            getCityWeatherByLatLng(lat, lng)
        }
    }

    private fun getCityWeatherByLatLng(lat: Double, lng: Double, cityId: Int? = null) {
        if (isConnectedToInternet()) {
            viewModelScope.launch {
                _viewState.value = Loading
                val result = getCityWeatherUseCase.perform(
                    GetCityInfoUseCase.Params(lat = lat, lng = lng)
                )
                result?.let { response ->
                    _viewState.value = Success(response)
                    val cityResponse = response.copy(id = cityId)
                    addOrUpdateCity(cityResponse, cityId, lat, lng)
                } ?: run {
                    _viewState.value = Error("Error")
                }
            }
        }
    }

    private fun silentRefreshCityWeather(cityId: Int, lat: Double, lng: Double) {
        viewModelScope.launch {
            val result = getCityWeatherUseCase.perform(
                GetCityInfoUseCase.Params(cityId = cityId, lat = lat, lng = lng)
            )
            result?.let { cityInfo ->
                _viewState.value = Success(cityInfo)
                getCityWeatherByLatLng(lat, lng, cityId)
                getForecast(cityId, lat, lng)
            } ?: run {
                _viewState.value = Error("Error")
            }
        }
    }

    private fun addOrUpdateCity(cityInfo: City, cityId: Int?, lat: Double, lng: Double) {
        viewModelScope.launch {
            cityId?.let { id ->
                updateCityInfoUseCase.perform(UpdateCityInfoUseCase.Params(cityInfo))
                _viewState.value = Updated(cityInfo.cityName)
            } ?: run {
                val listSize = saveCityInfoUseCase.perform(SaveCityInfoUseCase.Params(cityInfo))
                _viewState.value = Saved(cityInfo.cityName)
                getForecast(listSize.toInt(), lat, lng)
            }
        }
    }

    private fun getForecast(cityId: Int, lat: Double, lng: Double) {
        viewModelScope.launch {
            val result = getForecastListUseCase.perform(GetForecastListUseCase.Params(cityId))
            if (result.isNotEmpty()) {
                _viewState.value = ForecastSuccess(result, measurementUnit)
            } else {
                _viewState.value = Error("Forecasts not found")
            }
            fetchForecast(cityId, lat, lng)
        }
    }

    private fun fetchForecast(cityId: Int, lat: Double, lng: Double) {
        if (isConnectedToInternet()) {
            viewModelScope.launch {
                val result = getForecastUseCase.perform(
                    GetForecastUseCase.Params(cityId = cityId, lat = lat, lng = lng)
                )
                result?.let { forecastList ->
                    _viewState.value = ForecastFetched(forecastList, measurementUnit)
                    val listSize =
                        saveForecastUseCase.perform(SaveForecastUseCase.Params(forecastList))
                    _viewState.value = ForecastsSaved
                    print(listSize)
                } ?: run {
                    _viewState.value = Error("Forecast fetch Error")
                }
            }
        }
    }

    fun getMeasurementUnit() {
        viewModelScope.launch {
            measurementUnit = prefs.getWeatherPattern()
        }
    }


    sealed class CityViewState {
        object Loading : CityViewState()
        object ForecastsSaved : CityViewState()
        class Error(val msg: String) : CityViewState()
        class Success(val cityInfo: City) : CityViewState()
        class Saved(val cityName: String) : CityViewState()
        class Updated(val cityName: String) : CityViewState()
        class ForecastSuccess(val forecastList: List<Forecast>, val unit: MeasurementUnit) :
            CityViewState()

        class ForecastFetched(val forecastList: List<Forecast>, val unit: MeasurementUnit) :
            CityViewState()
    }
}