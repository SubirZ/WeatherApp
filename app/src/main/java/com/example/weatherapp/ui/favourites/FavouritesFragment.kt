package com.example.weatherapp.ui.favourites

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.City
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentFavouritesBinding
import com.example.weatherapp.extension.showSnackBar
import com.example.weatherapp.listeners.FavouriteItemClickListener
import com.example.weatherapp.ui.base.BaseFragment
import com.example.weatherapp.ui.favourites.FavouritesVM.FavouritesViewState.*
import com.example.weatherapp.ui.settings.MeasurementUnit
import com.example.weatherapp.ui.settings.SettingsBottomSheet
import com.example.weatherapp.ui.settings.UnitMeasurementMediator
import com.example.weatherapp.utils.PrefsUtils
import com.example.weatherapp.utils.SwipeToDeleteCallback
import kotlinx.coroutines.launch
import java.util.prefs.Preferences
import javax.inject.Inject

class FavouritesFragment : BaseFragment<FragmentFavouritesBinding, FavouritesVM>() {

    var favouritesAdapter: FavouritesAdapter? = null
    private var cityList = ArrayList<City>()

    override fun getViewModelClass(): Class<FavouritesVM> = FavouritesVM::class.java

    override fun layoutId(): Int = R.layout.fragment_favourites

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initListeners()
        setupObserver()
    }

    override fun onStart() {
        super.onStart()
        viewModel.getMeasurementUnit()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCityList()
    }

    private fun initUi() {
        binding.textTitle.text = getString(R.string.locations)
        setupRecycler()
    }

    private fun setupRecycler() {
        favouritesAdapter = FavouritesAdapter(object : FavouriteItemClickListener {
            override fun onItemClick(position: Int, lat: Double, lng: Double, cityId: Int) {
                navigateToCityInfo(cityId, lat, lng)
            }
        })
        binding.recyclerFavourites.adapter = favouritesAdapter
        val swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                cityList[viewHolder.adapterPosition].id?.let { viewModel.removeCity(it) }
                cityList.remove(cityList[viewHolder.adapterPosition])
                favouritesAdapter?.notifyItemRemoved(viewHolder.adapterPosition)
                if (cityList.isEmpty()) {
                    showEmptyView(true)
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding.recyclerFavourites)
    }

    private fun initListeners() {
        binding.apply {
            fabAddCity.setOnClickListener {
                navigateTo(R.id.action_favourites_to_addCity)
            }
            imageSettings.setOnClickListener {
                SettingsBottomSheet.newInstance(object : UnitMeasurementMediator {
                    override fun getCurrentMeasurementUnit(): MeasurementUnit {
                        return viewModel.measurementUnit
                    }

                    override fun setMeasurementPreference(unit: MeasurementUnit) {
                        setMeasurementUnitForAdapter(unit)
                        viewModel.updateMeasurementUnit(unit)
                    }

                }).show(parentFragmentManager, null)
            }
        }
    }

    private fun setupObserver() {
        viewModel.viewState
            .observe(viewLifecycleOwner) { viewState ->
                when (viewState) {
                    Loading -> showLoader(true)
                    Empty -> {
                        showEmptyView(true)
                        showLoader()
                    }
                    is Success -> {
                        showEmptyView(false)
                        showLoader()
                        cityList.clear()
                        cityList.addAll(viewState.cityList)
                        favouritesAdapter?.addItems(cityList)
                        setMeasurementUnitForAdapter(viewState.unit)
                    }
                    is MeasurementUnitState -> {
                        viewModel.updateMeasurementUnit(viewState.measurementUnit)
                    }
                }
            }
    }

    private fun setMeasurementUnitForAdapter(unit: MeasurementUnit) {
        favouritesAdapter?.setMeasurementUnit(unit)
    }

    private fun showEmptyView(show: Boolean) {
        binding.emptyView.isVisible = show
        binding.recyclerFavourites.isVisible = show.not()
    }

    private fun navigateToCityInfo(cityId: Int, lat: Double, lng: Double) {
        val action = FavouritesFragmentDirections
            .actionFavouritesToCity(
                cityId = cityId.toString(),
                latitude = lat.toString(),
                longitude = lng.toString()
            )
        navigateTo(action)
    }

    private fun showLoader(show: Boolean = false) {
        binding.loader.isVisible = show
    }
}