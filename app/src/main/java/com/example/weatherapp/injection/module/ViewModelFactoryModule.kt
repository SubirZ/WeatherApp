package com.example.weatherapp.injection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.injection.scope.ViewModelScope
import com.example.weatherapp.ui.addcity.AddCityVM
import com.example.weatherapp.ui.city.CityVM
import com.example.weatherapp.ui.home.HomeVM
import com.example.weatherapp.ui.favourites.FavouritesVM
import com.example.weatherapp.utils.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory):
            ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelScope(HomeVM::class)
    abstract fun bindHomeVM(homeVM: HomeVM) : ViewModel

    @Binds
    @IntoMap
    @ViewModelScope(FavouritesVM::class)
    abstract fun bindFavouritesVM(favouritesVM: FavouritesVM) : ViewModel

    @Binds
    @IntoMap
    @ViewModelScope(CityVM::class)
    abstract fun bindCityVM(cityVM: CityVM) : ViewModel

    @Binds
    @IntoMap
    @ViewModelScope(AddCityVM::class)
    abstract fun bindAddCityVM(addCityVM: AddCityVM) : ViewModel
}