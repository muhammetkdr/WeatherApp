package com.muhammetkdr.weatherapp.ui.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
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
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.DatesAndTimes
import com.muhammetkdr.weatherapp.location.DefaultLocationClient
import com.muhammetkdr.weatherapp.ui.home.nestedrv.HomeParentForecastWeatherAdapter
import dagger.hilt.android.AndroidEntryPoint
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
            navigateHomeFragmentSelf()
        }
    }

    fun navigateHomeFragmentSelf() {
        val action = HomeFragmentDirections.actionHomeFragmentSelf()
        findNavController().navigate(action)
    }

    private fun locationDataDecider() {
        if (args.selectedCity == null) {
            initRequestLocationPermissionLauncher()
            requestLocationPermission()
        } else {
            args.selectedCity?.let {
                viewModel.getMappedCurrentWeather(it.latitude, it.longitude)
                viewModel.getMappedForecastWeather(it.latitude, it.longitude)
            }
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
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED -> {
                getLocation()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) ||
                    shouldShowRequestPermissionRationale(
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) -> {
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
    private fun getLocation() = lifecycleScope.launchWhenStarted {
        try {
            defaultLocationClient.getLocationUpdates(LOCATION_REQUEST_DURATION).collect {
                viewModel.apply {
                    getMappedCurrentWeather(it.latitude, it.longitude)
                    getMappedForecastWeather(it.latitude, it.longitude)
                }
            }
        } catch (e: Exception) {
            Snackbar.make(
                requireView(),
                e.localizedMessage ?: getString(R.string.gps_orNetwork_disabled),
                Snackbar.LENGTH_INDEFINITE
            ).apply {
                setAction(R.string.REFRESH) {
                    navigateHomeFragmentSelf()
                }
                show()
            }
        }
    }

    private fun observeCurrentWeatherData() = lifecycleScope.launchWhenStarted {
        viewModel.currentWeather.collect { Resource ->
            when (Resource) {
                is Resource.Success -> {
                    Resource.data.let {
                        with(binding) {
                            homeProgressbar.gone()
                            containerCurrentWeather.weatherEntity = it
//                          root.setBackgroundResource(it.getBackground())
                        }
                    }
                }
                is Resource.Loading -> {
                    binding.homeProgressbar.visible()
                }
                is Resource.Error -> {
                    binding.homeProgressbar.visible()
                    requireView().showSnackbar(Resource.error)
                }
            }
        }
    }

    private fun observeForecastWeatherData() = lifecycleScope.launchWhenStarted {
        viewModel.forecastWeather.collect { Resource ->
            when (Resource) {
                is Resource.Success -> {
                    Resource.data.let {
                        binding.homeProgressbar.gone()
                        val list = it.uiDataMapper()
                        parentAdapter.submitList(list)
                    }
                }
                is Resource.Error -> {
                    binding.homeProgressbar.visible()
                    requireView().showSnackbar(Resource.error)
                }
                is Resource.Loading -> {
                    binding.homeProgressbar.visible()
                }
            }
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

    private fun initDataBinding() = with(binding) {
        lifecycleOwner = viewLifecycleOwner
        containerCurrentWeather.lifecycleOwner = viewLifecycleOwner
    }

    private fun initRvAdapter() = with(binding) {
        rvWeatherHome.adapter = parentAdapter
    }

    private fun parentRvItemClick(data: DatesAndTimes) {
        requireContext().toastBuilder("You clicked ${data.dayOfTheWeek}!")
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(data)
        findNavController().navigate(action)
    }
}