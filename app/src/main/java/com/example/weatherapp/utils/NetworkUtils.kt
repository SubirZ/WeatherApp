package com.example.weatherapp.utils

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.weatherapp.utils.NetworkType.*

class NetworkUtilImpl(private val cm: ConnectivityManager) : NetworkUtil {

    override fun isConnectedToInternet(): Boolean {
        return getConnectionType() != NO_INTERNET
    }

    override fun getConnectionType(): NetworkType {
        var result = NO_INTERNET
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm.run {
                getNetworkCapabilities(activeNetwork)?.run {
                    when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                            result = WIFI
                        }
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                            result = MOBILE
                        }
                        hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> {
                            result = VPN
                        }
                    }
                }
            }
        } else {
            cm.run {
                activeNetworkInfo?.run {
                    when (type) {
                        ConnectivityManager.TYPE_WIFI -> {
                            result = WIFI
                        }
                        ConnectivityManager.TYPE_MOBILE -> {
                            result = MOBILE
                        }
                        ConnectivityManager.TYPE_VPN -> {
                            result = VPN
                        }
                    }
                }
            }
        }
        return result
    }
}

enum class NetworkType {
    NO_INTERNET,
    MOBILE,
    WIFI,
    VPN
}

interface NetworkUtil {
    fun isConnectedToInternet(): Boolean
    fun getConnectionType(): NetworkType
}