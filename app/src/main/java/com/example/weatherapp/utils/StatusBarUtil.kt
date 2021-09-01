package com.example.weatherapp.utils

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.WindowManager

object StatusBarUtil {

    fun setTransparentStatusBar(activity: Activity) {
        activity.window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                )

        // below marshmallow add translucent status bar to show actions properly
        if (!isMarshmallowAndAbove()) {
            activity.window.setFlags(
                WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS,
                WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
            )
        }
    }

    private fun isMarshmallowAndAbove() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
}