package com.example.weatherapp.extension

import com.example.weatherapp.ui.settings.MeasurementUnit
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.roundToLong

const val CELSIUS_VALUE = 273.15
const val CELSIUS_UNIT = "°C"
const val KELVIN_UNIT = "K"
const val PERCENT = "%"
const val KMPH = "Km/h"
const val MPH = "m/h"
const val HPA = "hPa"

fun Double.getTemperature(unit: MeasurementUnit): String {
    return when (unit) {
        MeasurementUnit.IMPERIAL -> this.convertKelvinToCelsius()
        MeasurementUnit.METRIC -> this.formatKelvinValue()
    }
}

fun Double.convertKelvinToCelsius(): String {
    val celsiusValue = this.minus(CELSIUS_VALUE).roundToInt()
    val celsiusValueStr = celsiusValue.toString()
    return celsiusValueStr.plus(CELSIUS_UNIT)
}

fun Double.formatKelvinValue(): String {
    val fahrenheitValue = this.roundToInt()
    val fahrenheitValueStr = fahrenheitValue.toString()
    return fahrenheitValueStr.plus(KELVIN_UNIT)
}

fun Double.inPercentage(): String {
    return this.roundToInt().toString().plus(PERCENT)
}

fun Double.getSpeed(unit: MeasurementUnit): String {
    return when (unit) {
        MeasurementUnit.IMPERIAL -> this.inMilesPerHour()
        MeasurementUnit.METRIC -> this.inKmPerHour()
    }
}

fun Double.inKmPerHour(): String {
    return this.toString().plus(KMPH)
}

fun Double.inMilesPerHour(): String {
    return (this * 0.62).roundOffDecimal().toString().plus(MPH)
}

fun Double.inPressureValue(): String {
    return this.roundToInt().toString().plus(HPA)
}

fun Double.roundOffDecimal(): Double {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.CEILING
    return df.format(this).toDouble()
}