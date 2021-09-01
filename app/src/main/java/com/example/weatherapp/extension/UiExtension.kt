package com.example.weatherapp.extension

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.weatherapp.R
import com.google.android.material.snackbar.Snackbar

fun String.getWeatherColor(context: Context): Int {
    return when (this) {
        "clear sky" -> getColor(context, R.color.temp_breeze)
        "few clouds" -> getColor(context, R.color.temp_cool)
        "scattered clouds" -> getColor(context, R.color.temp_breeze)
        "broken clouds" -> getColor(context, R.color.temp_warm)
        "shower rain" -> getColor(context, R.color.temp_cool)
        "rain" -> getColor(context, R.color.temp_cloudy)
        "thunderstorm" -> getColor(context, R.color.temp_super_hot)
        "snow" -> getColor(context, R.color.temp_ice)
        else -> getColor(context, R.color.temp_cloudy)
    }
}

fun Int.getTemperatureColor(context: Context): Int {
    return when (this) {
        in 273..278 -> getColor(context, R.color.temp_ice)
        in 279.. 283 -> getColor(context, R.color.temp_cool)
        in 284.. 288 -> getColor(context, R.color.temp_breeze)
        in 289.. 293 -> getColor(context, R.color.temp_warm)
        in 284.. 298 -> getColor(context, R.color.temp_warmer)
        in 299.. 303 -> getColor(context, R.color.temp_hot)
        in 304.. 313 -> getColor(context, R.color.temp_super_hot)
        else -> getColor(context, R.color.temp_cloudy)
    }
}

fun getDrawable(context: Context, id: Int): Drawable? {
    return ContextCompat.getDrawable(context, id)
}

fun getColor(context: Context, id: Int): Int {
    return ContextCompat.getColor(context, id)
}

fun Fragment.showSnackBar(
    message: String,
    duration: Int = Snackbar.LENGTH_LONG
) {
    this.view?.showSnackBar(message, duration)
}

fun View.showSnackBar(
    message: String,
    duration: Int = Snackbar.LENGTH_LONG
) {
    Snackbar.make(this, message, duration)
        .show()
}