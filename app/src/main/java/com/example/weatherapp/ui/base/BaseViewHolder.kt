package com.example.weatherapp.ui.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<out B: ViewDataBinding>(val binding: B) : RecyclerView.ViewHolder(binding.root) {

    protected fun context() = binding.root.context
}