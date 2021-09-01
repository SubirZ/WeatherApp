package com.example.weatherapp.ui.city

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.example.domain.City
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentCityBinding
import com.example.weatherapp.extension.*
import com.example.weatherapp.ui.base.BaseFragment
import com.example.weatherapp.ui.city.CityVM.CityViewState.*

class CityFragment : BaseFragment<FragmentCityBinding, CityVM>() {
    private val args: CityFragmentArgs by navArgs()
    var forecastAdapter: ForecastAdapter? = null

    override fun getViewModelClass(): Class<CityVM> = CityVM::class.java

    override fun layoutId(): Int = R.layout.fragment_city

    override fun onStart() {
        super.onStart()
        viewModel.getMeasurementUnit()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        initData()
        initObservers()
    }

    private fun setupRecycler() {
        forecastAdapter = ForecastAdapter()
        binding.recyclerForecast.adapter = forecastAdapter
    }

    private fun initData() {
        viewModel.getCityWeather(
            args.cityId,
            args.latitude.toDouble(),
            args.longitude.toDouble()
        )
    }

    private fun initObservers() {
        viewModel.viewState
            .observe(viewLifecycleOwner) { viewState ->
                when (viewState) {
                    is Loading -> showLoader(true)
                    is Error -> showLoader()
                    is Success -> {
                        showLoader()
                        updateUI(viewState.cityInfo)
                    }
                    is Saved -> {
                        showSnackBar("${viewState.cityName}: Location added successfully")
                    }
                    is Updated -> {
                        showSnackBar("${viewState.cityName}: Location updated successfully")
                    }
                    is ForecastSuccess -> {
                        forecastAdapter?.addItems(viewState.forecastList, viewState.unit)
                        Log.d("Status: ", "ForecastSuccess")
                    }
                    is ForecastFetched -> {
                        forecastAdapter?.addItems(viewState.forecastList, viewState.unit)
                        Log.d("Status: ", "ForecastFetched")
                    }
                    is ForecastsSaved -> Log.d("Status: ", "ForecastsSaved")
                }
            }
    }

    private fun updateUI(cityInfo: City) {
        binding.apply {
            cityInfo.apply {
                lastTemperature?.toInt()?.let {
                    root.setBackgroundColor(it.getTemperatureColor(root.context))
                }
                weatherImage?.let {
                    imgWeather.loadImage(it.createImageUrl())
                }
                textTitle.text = cityName
                textWeather.text = weatherCondition
                textTemperature.text = lastTemperature?.getTemperature(viewModel.measurementUnit)
                textRealFeel.text = realFeel?.getTemperature(viewModel.measurementUnit)
                textHumidity.text = humidity?.inPercentage()
                textWindSpeed.text = windSpeed?.getSpeed(viewModel.measurementUnit)
                textPressure.text = pressure?.inPressureValue()
                textRainChances.text = rainChances?.inPercentage()
            }
        }
    }

    private fun showLoader(show: Boolean = false) {
        binding.loader.isVisible = show
    }
}