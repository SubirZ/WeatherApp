package com.example.weatherapp.utils

import android.content.SharedPreferences
import androidx.annotation.WorkerThread
import com.example.weatherapp.ui.settings.MeasurementUnit
import com.example.weatherapp.ui.settings.MeasurementUnit.IMPERIAL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@WorkerThread
class PrefsUtils(private val prefs: SharedPreferences) {

    suspend fun saveWeatherPattern(measurementUnit: MeasurementUnit) {
        withContext(Dispatchers.IO) {
            prefs.edit().putString(CURRENT_MEASUREMENT_UNIT, measurementUnit.name).commit()
        }
    }

    suspend fun getWeatherPattern(): MeasurementUnit {
        return withContext(Dispatchers.IO) {
            prefs.getString(CURRENT_MEASUREMENT_UNIT, IMPERIAL.name)?.let { patternString ->
                MeasurementUnit.valueOf(patternString)
            }!!
        }
    }

    companion object {
        const val CURRENT_MEASUREMENT_UNIT = "current_measurement_unit"
    }
}