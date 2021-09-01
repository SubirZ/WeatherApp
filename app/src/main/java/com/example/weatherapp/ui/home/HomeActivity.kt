package com.example.weatherapp.ui.home

import android.os.Bundle
import androidx.navigation.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityHomeBinding
import com.example.weatherapp.ui.base.BaseActivity
import com.example.weatherapp.utils.StatusBarUtil

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeVM>() {

    override fun getViewModelClass(): Class<HomeVM> = HomeVM::class.java

    override fun layoutId(): Int = R.layout.activity_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setTransparentStatusBar(this)
    }

    override fun onBackPressed() {
        val backStackEntryCount = supportFragmentManager.findFragmentById(R.id.nav_host_container)
            ?.childFragmentManager?.backStackEntryCount
        backStackEntryCount?.let { count ->
            when (count) {
                0 -> super.onBackPressed()
                else -> findNavController(R.id.nav_host_container).navigateUp()
            }
        }
    }
}