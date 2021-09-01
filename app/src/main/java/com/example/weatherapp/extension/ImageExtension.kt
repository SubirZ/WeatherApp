package com.example.weatherapp.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

const val IMAGE_URL_BASE = "https://openweathermap.org/img/wn/"
const val IMAGE_URL_EXTENSION = "@2x.png"

fun ImageView.loadImage(any: Any) {
    Glide.with(this.context)
        .load(any)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .into(this)
}

fun String.createImageUrl(): String {
    return IMAGE_URL_BASE.plus(this).plus(IMAGE_URL_EXTENSION)
}