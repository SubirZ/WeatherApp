package com.example.weatherapp.injection.module

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.weatherapp.injection.qualifiers.ApplicationContext
import com.example.weatherapp.utils.PrefsUtils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferenceModule {

    @Provides
    @Singleton
    internal fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Provides
    @Singleton
    internal fun providePrefsUtils(
        sharedPreferences: SharedPreferences
    ): PrefsUtils {
        return PrefsUtils(sharedPreferences)
    }
}