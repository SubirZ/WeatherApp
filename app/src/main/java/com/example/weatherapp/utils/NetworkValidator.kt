package com.example.weatherapp.utils

import android.content.Context
import android.view.View
import com.example.weatherapp.R
import com.example.weatherapp.extension.showSnackBar

object NetworkValidator {

    fun checkNetworkConnection(view: View, context: Context, networkUtil: NetworkUtil, feedbackNeeded: Boolean = true): Boolean {
        val isConnected = networkUtil.isConnectedToInternet()
        if (!isConnected && feedbackNeeded) {
            view.showSnackBar(context.getString(R.string.no_internet))
        }
        return isConnected
    }

    fun checkNetworkConnection(networkUtil: NetworkUtil): Boolean {
        return networkUtil.isConnectedToInternet()
    }
}