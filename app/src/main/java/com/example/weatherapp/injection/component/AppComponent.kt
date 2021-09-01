package com.example.weatherapp.injection.component

import com.example.weatherapp.WeatherApplication
import com.example.weatherapp.injection.module.*
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBindingModule::class,
        FragmentBindingModule::class,
        ViewModelFactoryModule::class,
        SourceModule::class,
        NetworkModule::class,
        PreferenceModule::class,
        DatabaseModule::class,
        RepositoryModule::class,
        UseCaseModule::class
    ]
)
interface AppComponent : AndroidInjector<WeatherApplication> {
    @Component.Factory
    interface Factory : AndroidInjector.Factory<WeatherApplication>
}