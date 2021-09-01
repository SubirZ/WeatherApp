package com.example.weatherapp.widgets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.weatherapp.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class RoundedCornerBottomSheet<VB : ViewDataBinding> : BottomSheetDialogFragment() {

    private var _binding: VB? = null
    val binding: VB
        get() = _binding!!

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun setupBindings(binding: VB)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)

        setupBindings(binding)

        return _binding?.root
    }

    override fun onStart() {
        super.onStart()

        view?.post {
            val parent = view?.parent as View
            parent.background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_bottom_sheet)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}