package com.example.weatherapp.injection.module

import android.content.Context
import android.net.ConnectivityManager
import com.example.weatherapp.WeatherApplication
import com.example.weatherapp.injection.qualifiers.ApplicationContext
import com.example.weatherapp.utils.NetworkUtil
import com.example.weatherapp.utils.NetworkUtilImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @ApplicationContext
    fun provideAppContext(app: WeatherApplication): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideNetworkUtils(connectivityManager: ConnectivityManager)
    : NetworkUtil {
     return NetworkUtilImpl(connectivityManager)
    }

    @Provides
    @Singleton
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}