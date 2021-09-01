package com.example.weatherapp.ui.settings

import android.content.Context
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentSettingsBinding
import com.example.weatherapp.widgets.RoundedCornerBottomSheet

class SettingsBottomSheet(private val mediator: UnitMeasurementMediator) : RoundedCornerBottomSheet<FragmentSettingsBinding>() {
    private var _mediator: UnitMeasurementMediator? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        _mediator = mediator
    }

    override fun getLayoutId(): Int = R.layout.fragment_settings

    override fun setupBindings(binding: FragmentSettingsBinding) {
        val unit = _mediator?.getCurrentMeasurementUnit() ?: MeasurementUnit.IMPERIAL

        if (unit == MeasurementUnit.IMPERIAL) {
            binding.imperialSelectorImg.setImageResource(R.drawable.ic_selected)
            binding.metricSelectorImg.setImageResource(R.drawable.ic_unselected)
        } else {
            binding.metricSelectorImg.setImageResource(R.drawable.ic_selected)
            binding.imperialSelectorImg.setImageResource(R.drawable.ic_unselected)
        }

        binding.imperialLayout.setOnClickListener {
            _mediator?.setMeasurementPreference(MeasurementUnit.IMPERIAL)
            dismiss()
        }

        binding.metricLayout.setOnClickListener {
            _mediator?.setMeasurementPreference(MeasurementUnit.METRIC)
            dismiss()
        }
    }

    companion object {
        fun newInstance(mediator: UnitMeasurementMediator): SettingsBottomSheet {
            return SettingsBottomSheet(mediator)
        }
    }
}

interface UnitMeasurementMediator {
    fun getCurrentMeasurementUnit(): MeasurementUnit
    fun setMeasurementPreference(unit: MeasurementUnit)
}

enum class MeasurementUnit {
    IMPERIAL,
    METRIC
}