package com.example.usecases.extension

import java.text.SimpleDateFormat
import java.util.*

fun Long.convertLongToDate(): String {
    val date = Date(this * 1000)
    val format = SimpleDateFormat( "EEEE, dd MMM", Locale.getDefault())
    return format.format(date)
}