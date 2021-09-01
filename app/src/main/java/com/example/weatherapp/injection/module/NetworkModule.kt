package com.example.weatherapp.injection.module

import com.example.data.remote.RetrofitHelper
import com.example.data.remote.WeatherApis
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.utils.jsonadaptors.PointFJsonAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            httpBuilder.interceptors().add(httpLoggingInterceptor)
        }
        return httpBuilder.build()
    }

    @Provides @Singleton fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(PointFJsonAdapter())
            .build()
    }

    @Provides
    @Singleton
    internal fun provideRestAdapter(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit {
        return RetrofitHelper.createRetrofitClient(okHttpClient, moshi, BuildConfig.API_BASE_URL)
    }

    @Provides
    @Singleton
    internal fun provideWeatherApis(retrofit: Retrofit): WeatherApis {
        return WeatherApis.createRetrofitService(retrofit, BuildConfig.WEATHER_API_KEY)
    }
}