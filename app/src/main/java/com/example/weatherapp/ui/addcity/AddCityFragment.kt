package com.example.weatherapp.ui.addcity

import android.location.Geocoder
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentAddCityBinding
import com.example.weatherapp.ui.base.BaseFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*

class AddCityFragment : BaseFragment<FragmentAddCityBinding, AddCityVM>(), OnMapReadyCallback,
    GoogleMap.OnMarkerDragListener {

    companion object {
        const val INITIAL_LAT = 17.39
        const val INITIAL_LNG = 78.49
        const val INITIAL_LOCATION = "Hyderabad"
    }

    private var positionLatLng: LatLng = LatLng(INITIAL_LAT, INITIAL_LNG) // initial position of marker omn map

    override fun getViewModelClass(): Class<AddCityVM> = AddCityVM::class.java

    override fun layoutId(): Int = R.layout.fragment_add_city

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMaps()
        initView()
        initListener()
    }

    private fun initView() {
        binding.apply {
            textDetectedCity.text = INITIAL_LOCATION
        }
    }

    private fun initListener() {
        binding.apply {
            btnAdd.setOnClickListener {
                if (isConnectedToInternet()) {
                    navigateToCityInfoScreen()
                }
            }
            imageBack.setOnClickListener { findNavController().navigateUp() }
        }
    }

    private fun navigateToCityInfoScreen() {
        val action = AddCityFragmentDirections
            .actionAddCityFragmentToCityFragment(
                latitude = positionLatLng.latitude.toString(),
                longitude = positionLatLng.longitude.toString()
            )
        navigateTo(action)
    }

    private fun initMaps() {
        // Get the SupportMapFragment and request notification when the map is ready to be used.
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        // Animating to the touched position
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(positionLatLng))
        googleMap.addMarker(
            MarkerOptions().draggable(true)
                .position(positionLatLng)
                .title(INITIAL_LOCATION)
        )
        googleMap.setOnMarkerDragListener(this)
    }

    override fun onMarkerDragStart(p0: Marker) {
        binding.textDragMarkerInstruction.isVisible = false
    }

    override fun onMarkerDrag(p0: Marker) {
        // unused
    }

    override fun onMarkerDragEnd(marker: Marker) {
        if (isConnectedToInternet()) {
            positionLatLng = marker.position
            val geoCoder = Geocoder(context, Locale.getDefault())
            val addressList = geoCoder.getFromLocation(
                positionLatLng.latitude,
                positionLatLng.longitude,
                1
            )
            if (addressList.isNotEmpty()) {
                marker.title = addressList[0].getAddressLine(0)
                marker.title?.let { title ->
                    updateView(title)
                }
            }
        }
    }

    private fun updateView(title: String) {
        binding.textDetectedCity.text = title
    }
}