package com.muhammetkdr.weatherapp.ui.home

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.muhammetkdr.weatherapp.R
import com.muhammetkdr.weatherapp.base.BaseFragment
import com.muhammetkdr.weatherapp.common.extensions.*
import com.muhammetkdr.weatherapp.common.utils.Const.Companion.LOCATION_PERMISSION_REQUEST_CODE_ZERO
import com.muhammetkdr.weatherapp.common.utils.Const.Companion.LOCATION_REQUEST_DURATION
import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.databinding.FragmentHomeBinding
import com.muhammetkdr.weatherapp.location.DefaultLocationClient
import com.muhammetkdr.weatherapp.data.dto.forecast.WeatherList
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate
){
    override val viewModel by viewModels<HomeViewModel>()

    // todo @Inject adapter.. i couldn't find how to do it yet..
    private val adapter: HomeWeatherAdapter by lazy { HomeWeatherAdapter(::navigateWeatherPage) }

    @Inject
    lateinit var defaultLocationClient: DefaultLocationClient

    @Inject
    lateinit var calendar: Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermission()
    }

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

        observeCurrentWeatherData()
        observeForecastWeatherData()

        initDataBinding()
        getLocation()
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() = lifecycleScope.launchWhenStarted {
        try {
            defaultLocationClient.getLocationUpdates(LOCATION_REQUEST_DURATION).collect {
                viewModel.getCurrentWeather(it.latitude, it.longitude)
                viewModel.getForecastWeather(it.latitude, it.longitude)
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
                        binding.homeProgressbar.gone()

                        val (date, month, year) = calendar
                        val dateFormatted = date.toString().formatCallendar()
                        val monthFormatted = month.toString().formatCallendar()
                        binding.customToolBar.updateTitle("$dateFormatted, $monthFormatted, $year")
                        binding.containerCurrentWeather.weatherResponse = it



                        //binding.containerCurrentWeather.imgViewCustomHome.setImageResource(R.drawable.background)
                        binding.textView.text =
                            it.name.toString() + "\n" + it.main?.temp?.toString() + "\n" + it.weather?.get(
                                0
                            )?.main + "\n" + it.weather?.get(0)?.description?.capitalizeWords()
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

    private fun observeForecastWeatherData() = lifecycleScope.launchWhenStarted {
        viewModel.forecastWeatherList.collect { Resource ->
            when (Resource) {
                is Resource.Success -> {
                    Resource.data.let { forecastWeatherResponse ->
                        binding.homeProgressbar.gone()
                        adapter.submitList(forecastWeatherResponse.list)
                        val xd = forecastWeatherResponse.list?.get(0)?.dtTxt
                        println(xd)
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

    private fun initDataBinding() = with(binding) {
        rvWeatherHome.adapter = adapter
        rvWeatherHome.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        lifecycleOwner = viewLifecycleOwner
        containerCurrentWeather.lifecycleOwner = viewLifecycleOwner
    }

    private fun navigateWeatherPage(data: WeatherList) {

        //  findNavController().popBackStack()
    }

}

