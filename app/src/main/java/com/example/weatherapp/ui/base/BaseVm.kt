package com.example.weatherapp.ui.base

import androidx.lifecycle.ViewModel
import com.example.weatherapp.utils.NetworkUtil
import com.example.weatherapp.utils.NetworkValidator
import com.example.weatherapp.utils.PrefsUtils
import javax.inject.Inject

abstract class BaseVm : ViewModel() {

    @Inject
    lateinit var networkUtil: NetworkUtil

    @Inject
    lateinit var prefs: PrefsUtils

    fun isConnectedToInternet(): Boolean {
        return NetworkValidator.checkNetworkConnection(networkUtil)
    }

    override fun onCleared() {
        super.onCleared()
    }
}