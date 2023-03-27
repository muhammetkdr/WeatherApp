package com.muhammetkdr.weatherapp.ui.home

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.muhammetkdr.weatherapp.R
import com.muhammetkdr.weatherapp.base.BaseFragment
import com.muhammetkdr.weatherapp.common.extensions.*
import com.muhammetkdr.weatherapp.common.utils.Constants.LOCATION_REQUEST_DURATION
import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.databinding.FragmentHomeBinding
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.forecastuidata.DatesAndTimes
import com.muhammetkdr.weatherapp.location.DefaultLocationClient
import com.muhammetkdr.weatherapp.ui.home.nestedrv.HomeParentForecastWeatherAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate
) {
    override val viewModel by viewModels<HomeViewModel>()

    private val parentAdapter: HomeParentForecastWeatherAdapter by lazy {
        HomeParentForecastWeatherAdapter(
            ::parentRvItemClick
        )
    }

    private lateinit var requestLocationPermissionLauncher: ActivityResultLauncher<Array<String>>

    @Inject
    lateinit var calendar: Calendar

    @Inject
    lateinit var defaultLocationClient: DefaultLocationClient

    private val args: HomeFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeForecastWeatherData()
        observeCurrentWeatherData()
        observeCalendar()

        locationDataDecider()

        initDataBinding()
        initRvAdapter()

        handleCustomToolbarSearchPressed()
        handleBackPressed()
    }

    private fun handleBackPressed() {
        addOnBackPressedDispatcher {
            return@addOnBackPressedDispatcher
        }
    }

    private fun navigateHomeFragmentSelf() {
        val action = HomeFragmentDirections.actionHomeFragmentSelf()
        findNavController().navigate(action)
    }

    private fun locationDataDecider() {
        args.selectedCity?.let {
            viewModel.getMappedCurrentWeather(it.latitude, it.longitude)
            viewModel.getMappedForecastWeather(it.latitude, it.longitude)
        } ?: run {
            initRequestLocationPermissionLauncher()
            requestLocationPermission()
        }
    }

    private fun initRequestLocationPermissionLauncher() {
        requestLocationPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            val isFineLocationGranted =
                permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
            val isCoarseLocationGranted =
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false
            if (isFineLocationGranted && isCoarseLocationGranted) {
                getLocation()
            } else {
                requestLocationPermission()
            }
        }
    }

    private fun requestLocationPermission() {
        when {
            requireContext().hasLocationPermission() -> {
                getLocation()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) ||
                    shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)
            -> {
                Snackbar.make(requireView(), R.string.permission_need, Snackbar.LENGTH_INDEFINITE)
                    .apply {
                        setAction(R.string.give_permission) {
                            requestLocationPermissionLauncher.launch(
                                arrayOf(
                                    Manifest.permission.ACCESS_FINE_LOCATION,
                                    Manifest.permission.ACCESS_COARSE_LOCATION
                                )
                            )
                        }
                        show()
                    }
            }
            else -> {
                requestLocationPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        lifecycleScope.launch {
            try {
                defaultLocationClient.getLocationUpdates(LOCATION_REQUEST_DURATION).collect {
                    viewModel.apply {
                        getMappedCurrentWeather(it.latitude, it.longitude)
                        getMappedForecastWeather(it.latitude, it.longitude)
                    }
                }
            } catch (e: Exception) {
                    showSafeSnackbar(
                        e.localizedMessage ?: getString(R.string.gps_orNetwork_disabled),
                        getString(R.string.REFRESH)
                    ) {
                        navigateHomeFragmentSelf()
                    }
            }
        }
    }

    private fun observeCurrentWeatherData() {
        lifecycleScope.launch {
            viewModel.currentWeather.collectLatest { Resource ->
                when (Resource) {
                    is Resource.Success -> {
                        Resource.data.apply {
                            with(binding) {
                                setCurrentWeatherUiVisibility(true)
                                customHomeLayoutContainer.weatherEntity = this@apply
                            }
                        }
                    }
                    is Resource.Loading -> {
                        setCurrentWeatherUiVisibility(false)
                    }
                    is Resource.Error -> {
                        setCurrentWeatherUiVisibility(false)
                        requireView().showSnackbar(Resource.error)
                    }
                }
            }
        }
    }

    private fun observeForecastWeatherData() {
        lifecycleScope.launch {
            viewModel.forecastWeather.collectLatest { Resource ->
                when (Resource) {
                    is Resource.Success -> {
                        Resource.data.apply {
                            setForecastWeatherUiVisibility(true)
                            val list = this.uiDataMapper()
                            parentAdapter.submitList(list)
                        }
                    }
                    is Resource.Error -> {
                        setForecastWeatherUiVisibility(false)
                        requireView().showSnackbar(Resource.error)
                    }
                    is Resource.Loading -> {
                        setForecastWeatherUiVisibility(false)
                    }
                }
            }
        }
    }

    private fun setCurrentWeatherUiVisibility(isVisible: Boolean) {
        with(binding) {
            customToolBar.isVisible = isVisible
            lineDivider.isVisible = isVisible
            customHomeLayoutContainer.root.isVisible = isVisible
            homeCurrentWeatherProgressbar.isVisible = !isVisible
        }
    }

    private fun setForecastWeatherUiVisibility(isVisible: Boolean) {
        with(binding) {
            rvWeatherHome.isVisible = isVisible
            homeForecastWeatherProgressbar.isVisible = !isVisible
        }
    }

    private fun observeCalendar() {
        viewModel.getTodaysCalendar(calendar)
        viewModel.date.observeIfNotNull(viewLifecycleOwner) {
            binding.customToolBar.updateTitle(it)
        }
    }

    private fun handleCustomToolbarSearchPressed() {
        binding.customToolBar.onSearchButtonPressed {
            val action = HomeFragmentDirections.actionHomeFragmentToSearchFragment()
            findNavController().navigate(action)
        }
    }

    private fun initDataBinding() {
        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            customHomeLayoutContainer.lifecycleOwner = viewLifecycleOwner
        }
    }

    private fun initRvAdapter() {
        with(binding) {
            rvWeatherHome.adapter = parentAdapter
        }
    }

    private fun parentRvItemClick(data: DatesAndTimes) {
        requireContext().toastBuilder(data.dayOfTheWeek)
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(data)
        findNavController().navigate(action)
    }
}