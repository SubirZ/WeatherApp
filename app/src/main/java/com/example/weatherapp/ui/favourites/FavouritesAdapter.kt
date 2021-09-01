package com.example.weatherapp.ui.favourites

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.City
import com.example.weatherapp.databinding.ItemFavouriteBinding
import com.example.weatherapp.extension.convertKelvinToCelsius
import com.example.weatherapp.extension.getTemperature
import com.example.weatherapp.extension.getTemperatureColor
import com.example.weatherapp.listeners.FavouriteItemClickListener
import com.example.weatherapp.ui.base.BaseViewHolder
import com.example.weatherapp.ui.settings.MeasurementUnit

class FavouritesAdapter(private val itemClickListener: FavouriteItemClickListener) :
    RecyclerView.Adapter<FavouritesAdapter.FavouritesViewHolder>() {
    private var items: List<City> = emptyList()
    private var measurementUnit: MeasurementUnit = MeasurementUnit.IMPERIAL

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavouritesAdapter.FavouritesViewHolder {
        return FavouritesViewHolder(
            ItemFavouriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: FavouritesAdapter.FavouritesViewHolder, position: Int) {
        holder.binding.apply {
            val cityInfo = items[position]
            val backgroundColor =
                cityInfo.lastTemperature?.toInt()?.getTemperatureColor(root.context)
            backgroundColor?.let {
                parentLayout.setBackgroundColor(backgroundColor)
            }
            itemCityName.text = cityInfo.cityName
            itemLastTemperature.text = cityInfo.lastTemperature?.getTemperature(measurementUnit)
            itemMinTemp.text = cityInfo.minTemperature?.getTemperature(measurementUnit)
            itemMaxTemp.text = cityInfo.maxTemperature?.getTemperature(measurementUnit)
            parentLayout.setOnClickListener {
                cityInfo.id?.let {
                    itemClickListener.onItemClick(position, cityInfo.lat, cityInfo.lng, it)
                } ?: run {
                    Log.e("cityId: ", "missing")
                }
            }
        }
    }

    override fun getItemCount(): Int = items.size

    fun addItems(items: List<City>) {
        this.items = items
        notifyDataSetChanged()
    }

    @JvmName("setMeasurementUnit1")
    fun setMeasurementUnit(measurementUnit: MeasurementUnit) {
        this.measurementUnit = measurementUnit
        notifyDataSetChanged()
    }

    inner class FavouritesViewHolder(binding: ItemFavouriteBinding) :
        BaseViewHolder<ItemFavouriteBinding>(binding)
}