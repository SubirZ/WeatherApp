package com.example.weatherapp.ui.favourites

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.City
import com.example.usecases.weather.GetCityListUseCase
import com.example.usecases.weather.RemoveCityUseCase
import com.example.weatherapp.ui.base.BaseVm
import com.example.weatherapp.ui.favourites.FavouritesVM.FavouritesViewState.*
import com.example.weatherapp.ui.settings.MeasurementUnit
import com.example.weatherapp.utils.PrefsUtils
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouritesVM @Inject constructor(
    private val getCityListUseCase: GetCityListUseCase,
    private val removeCityUseCase: RemoveCityUseCase
) : BaseVm() {

    private val _viewState = MutableLiveData<FavouritesViewState>()
    val viewState: LiveData<FavouritesViewState> = _viewState

    var measurementUnit: MeasurementUnit = MeasurementUnit.IMPERIAL

    fun getCityList() {
        _viewState.value = Loading
        viewModelScope.launch {
            val cityList = getCityListUseCase.perform()
            if (cityList.isNotEmpty()) {
                _viewState.value = Success(cityList, measurementUnit)
            } else _viewState.value = Empty

            Log.i("CityList", cityList.toString())
        }
    }

    fun removeCity(cityId: Int) {
        viewModelScope.launch {
            removeCityUseCase.perform(RemoveCityUseCase.Params(cityId))
        }
    }

    fun getMeasurementUnit() {
        viewModelScope.launch {
            val unit = prefs.getWeatherPattern()
            measurementUnit = unit
            _viewState.value = MeasurementUnitState(unit)
        }
    }

    fun updateMeasurementUnit(unit: MeasurementUnit) {
        viewModelScope.launch {
            prefs.saveWeatherPattern(unit)
            measurementUnit = unit
        }
    }

    sealed class FavouritesViewState {
        object Loading : FavouritesViewState()
        object Empty : FavouritesViewState()
        class Success(val cityList: List<City>, val unit: MeasurementUnit) : FavouritesViewState()
        class MeasurementUnitState(val measurementUnit: MeasurementUnit) : FavouritesViewState()
    }
}