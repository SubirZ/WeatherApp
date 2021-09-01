package com.example.weatherapp.ui.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.BR
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<VB: ViewDataBinding, VM: BaseVm> : DaggerAppCompatActivity(), BaseUiComponent<VM> {

    private var _binding: VB? = null
    val binding: VB get() = _binding!!

    protected lateinit var viewModel: VM

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindContentView(layoutId())
    }

    // Bind the view and bind the viewModel to layout
    private fun bindContentView(layoutId: Int) {
        _binding = DataBindingUtil.setContentView(this, layoutId)
        viewModel = ViewModelProvider(this, viewModelFactory).get(getViewModelClass())
//        _binding?.setVariable(BR._all, viewModel)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}