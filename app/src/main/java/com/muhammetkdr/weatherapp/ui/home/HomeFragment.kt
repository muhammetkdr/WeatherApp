package com.muhammetkdr.weatherapp.ui.home

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.muhammetkdr.weatherapp.base.BaseFragment
import com.muhammetkdr.weatherapp.common.extensions.*
import com.muhammetkdr.weatherapp.common.utils.PermissionManager
import com.muhammetkdr.weatherapp.databinding.FragmentHomeBinding
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.forecastuidata.DatesAndTimes
import com.muhammetkdr.weatherapp.ui.home.nestedrv.HomeParentForecastWeatherAdapter
import com.muhammetkdr.weatherapp.ui.uistate.UiState
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

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

    private val permissionsManager by lazy {
        PermissionManager(
            activity = requireActivity(),
            permissions = arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.CAMERA
            ),
            onPermissionGranted = ::onPermissionGranted,
            onPermissionDenied = ::onPermissionDenied
        )
    }

    private fun onPermissionGranted() {
        getLocation()
    }

    private fun onPermissionDenied() {
        permissionsManager.requestPermissions()
    }

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

        viewModel.getTodaysCalendar()
    }

    private fun locationDataDecider() {
        args.selectedCity?.let {
            viewModel.getMappedCurrentWeather(it.latitude, it.longitude)
            viewModel.getMappedForecastWeather(it.latitude, it.longitude)
        } ?: run {
            permissionsManager.requestPermissions()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        viewModel.getCurrentLocation()
        collectFlow(viewModel.errorState) {
            it?.let {
                showSafeSnackbar(
                    it
                )
            }
        }
    }

    private fun observeCurrentWeatherData() {
        collectFlow(viewModel.currentWeather) {
            when (it) {
                is UiState.Success -> {
                    setCurrentWeatherUiVisibility(true)
                    binding.customHomeLayoutContainer.homeCurrentWeatherUiData = it.data
                }

                is UiState.Loading -> {
                    setCurrentWeatherUiVisibility(false)
                }

                is UiState.Error -> {
                    setCurrentWeatherUiVisibility(false)
                    requireView().showSnackbar(it.error)
                }
            }
        }
    }

    private fun observeForecastWeatherData() {
        collectFlow(viewModel.forecastWeather) {
            when (it) {
                is UiState.Success -> {
                    setForecastWeatherUiVisibility(true)
                    parentAdapter.submitList(it.data.forecastWeatherList)
                }

                is UiState.Loading -> {
                    setForecastWeatherUiVisibility(false)
                }

                is UiState.Error -> {
                    setForecastWeatherUiVisibility(false)
                    requireView().showSnackbar(it.error)
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
        collectFlow(viewModel.date) {
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