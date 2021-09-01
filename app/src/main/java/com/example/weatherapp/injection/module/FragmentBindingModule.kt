package com.example.weatherapp.injection.module

import com.example.weatherapp.ui.addcity.AddCityFragment
import com.example.weatherapp.ui.city.CityFragment
import com.example.weatherapp.ui.favourites.FavouritesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * To create dependencies for a specific fragments
 */
@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    internal abstract fun provideFavouritesFragment() : FavouritesFragment

    @ContributesAndroidInjector
    internal abstract fun provideCityFragment() : CityFragment

    @ContributesAndroidInjector
    internal abstract fun provideAddCityFragment() : AddCityFragment
}