package com.muhammetkdr.weatherapp.ui.home

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.muhammetkdr.weatherapp.R
import com.muhammetkdr.weatherapp.base.BaseFragment
import com.muhammetkdr.weatherapp.common.extensions.*
import com.muhammetkdr.weatherapp.common.utils.Const.Companion.LOCATION_PERMISSION_REQUEST_CODE_ZERO
import com.muhammetkdr.weatherapp.common.utils.Const.Companion.LOCATION_REQUEST_DURATION
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

    private val parentAdapter: HomeParentForecastWeatherAdapter by lazy { HomeParentForecastWeatherAdapter(::parentRvItemClick) }

    @Inject
    lateinit var calendar: Calendar

    @Inject
    lateinit var defaultLocationClient: DefaultLocationClient

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(), arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
            ), LOCATION_PERMISSION_REQUEST_CODE_ZERO
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeForecastWeatherData()
        observeCurrentWeatherData()
        observeCalendar()

        requestPermission()

        viewModel.getTodaysCallendar(calendar)

        getLocation()
        initDataBinding()
        initRvAdapter()
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
            requireView().showSnackbar(
                e.localizedMessage
                    ?: requireContext().resources.getString(R.string.gps_or_network_disabled)
            )
        }
    }

    private fun observeCurrentWeatherData() = lifecycleScope.launchWhenStarted {
        viewModel.currentWeather.collect { Resource ->
            when (Resource) {
                is Resource.Success -> {
                    Resource.data.let {
                        with(binding){
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
                        val list = it.getMappedWeatherList()
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

    private fun observeCalendar(){
        viewModel.date.observe(viewLifecycleOwner){
            binding.customToolBar.updateTitle(it)
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
        Toast.makeText(requireContext(), "You clicked ${data.dayOfTheWeek}!", Toast.LENGTH_SHORT).show()
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(data)
        findNavController().navigate(action)
    }
}