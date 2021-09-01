package com.example.weatherapp.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import com.example.weatherapp.utils.NetworkUtil
import com.example.weatherapp.utils.NetworkValidator
import com.example.weatherapp.utils.PrefsUtils
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<VB: ViewDataBinding, VM: BaseVm> : DaggerFragment(), BaseUiComponent<VM> {

    private var _binding: VB? = null
    val binding: VB get() = _binding!!

    protected lateinit var viewModel: VM

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var networkUtil: NetworkUtil

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutId(), container, false)

        viewModel = ViewModelProvider(this, viewModelFactory).get(getViewModelClass())

        return _binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun navigateTo(id: Int) {
        NavHostFragment.findNavController(this).navigate(id)
    }

    fun navigateTo(directions: NavDirections) {
        NavHostFragment.findNavController(this).navigate(directions)
    }

    fun isConnectedToInternet(): Boolean {
        return NetworkValidator.checkNetworkConnection(binding.root, binding.root.context, networkUtil)
    }
}