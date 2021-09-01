package com.example.weatherapp.injection.module

import com.example.weatherapp.ui.home.HomeActivity
import com.example.weatherapp.injection.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * To create dependencies for a specific activity
 */
@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindHomeActivity(): HomeActivity
}