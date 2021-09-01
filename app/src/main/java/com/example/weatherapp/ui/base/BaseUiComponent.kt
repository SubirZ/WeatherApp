package com.example.weatherapp.ui.base

import androidx.annotation.LayoutRes

interface BaseUiComponent<VM: BaseVm> {
    fun getViewModelClass(): Class<VM>

    @LayoutRes
    fun layoutId(): Int
}