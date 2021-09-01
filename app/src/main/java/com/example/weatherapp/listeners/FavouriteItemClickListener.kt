package com.example.weatherapp.listeners

interface FavouriteItemClickListener {
    fun onItemClick(position: Int, lat: Double, lng: Double, cityId: Int)
}