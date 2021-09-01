package com.example.weatherapp.ui.city

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Forecast
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ItemForecastBinding
import com.example.weatherapp.extension.getSpeed
import com.example.weatherapp.extension.getTemperature
import com.example.weatherapp.extension.getTemperatureColor
import com.example.weatherapp.extension.inPercentage
import com.example.weatherapp.ui.base.BaseViewHolder
import com.example.weatherapp.ui.city.ForecastAdapter.ForecastViewHolder
import com.example.weatherapp.ui.settings.MeasurementUnit

class ForecastAdapter : RecyclerView.Adapter<ForecastViewHolder>() {
    private var items = ArrayList<Forecast>()
    private var measurementUnit: MeasurementUnit = MeasurementUnit.IMPERIAL

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ForecastViewHolder {
        return ForecastViewHolder(
            ItemForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.binding.apply {
            val forecast = items[position]
            val backgroundColor =
                forecast.temperature.toInt().getTemperatureColor(root.context)
            backgroundColor.let {
                parentLayout.setBackgroundColor(backgroundColor)
            }

            itemDate.text = when (position) {
                0 -> root.context.getString(R.string.today)
                1 -> root.context.getString(R.string.tomorrow)
                else -> forecast.date
            }
            itemTemperature.text = forecast.temperature.getTemperature(measurementUnit)
            itemWindSpeed.text = root.context.getString(R.string.wind_speed)
                .plus(forecast.windSpeed.getSpeed(measurementUnit))
            itemRainChances.text = root.context.getString(R.string.rain_chances)
                .plus(forecast.rainChances.inPercentage())
            itemHumidity.text =
                root.context.getString(R.string.humidity).plus(forecast.humidity.inPercentage())
        }
    }

    override fun getItemCount(): Int = items.size

    fun addItems(items: List<Forecast>, unit: MeasurementUnit) {
        this.items.clear()
        this.items.addAll(items)
        measurementUnit = unit
        notifyDataSetChanged()
    }

    inner class ForecastViewHolder(binding: ItemForecastBinding) :
        BaseViewHolder<ItemForecastBinding>(binding)
}