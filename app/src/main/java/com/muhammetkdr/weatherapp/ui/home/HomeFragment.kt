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
    private val adapter: HomeWeatherAdapter by lazy { HomeWeatherAdapter(::forecastItemClick) }

    @Inject
    lateinit var defaultLocationClient: DefaultLocationClient

    @Inject
    lateinit var calendar: Calendar

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

        requestPermission()

        getLocation()
        initDataBinding()
        initRvAdapter()

    }

    @SuppressLint("MissingPermission")
    private fun getLocation() = lifecycleScope.launchWhenStarted {
        try {
            defaultLocationClient.getLocationUpdates(LOCATION_REQUEST_DURATION).collect {
                viewModel.getMappedForecastWeather(it.latitude, it.longitude)
                viewModel.getMappedWeather(it.latitude,it.longitude)
            }
        } catch (e: Exception) {
            requireView().showSnackbar(
                e.localizedMessage
                    ?: requireContext().resources.getString(R.string.gps_or_network_disabled)
            )
        }
    }

    private fun observeCurrentWeatherData() = lifecycleScope.launchWhenStarted {
        viewModel.currentWeatherMapped.collect{ Resource->
            when(Resource) {
                is Resource.Success-> {
                    Resource.data.let {
//                        binding.textView.text = "${it.name}\n ${it.main.temp} \n ${it.weather.get(0).description}\n ${it.name}\n ${it.sys.country} \n"
//                        binding.containerCurrentWeather.imgViewCustomHome.setImageResource(it.getBackgroud())
//                        binding.textView.text = it.backgroundDecider
                        binding.homeProgressbar.gone()

                        val (date, month, year) = calendar
                        val dateFormatted = date.toString().formatCallendar()
                        val monthFormatted = month.toString().formatCallendar()

                        binding.customToolBar.updateTitle("$dateFormatted, $monthFormatted, $year")

                        binding.containerCurrentWeather.weatherEntity = it

                        binding.root.setBackgroundResource(it.getBackground())
                    }
                }
                is Resource.Loading-> {
                    binding.homeProgressbar.visible()
                }
                is Resource.Error->{
                    binding.homeProgressbar.visible()
                    requireView().showSnackbar(Resource.error)
                }
            }

        }
    }

    private fun observeForecastWeatherData() = lifecycleScope.launchWhenStarted {
        viewModel.forecastWeatherMapped.collect { Resource ->
            when (Resource) {
                is Resource.Success -> {
                    Resource.data.let {
                        binding.homeProgressbar.gone()
                        adapter.submitList(it.list)
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
        lifecycleOwner = viewLifecycleOwner
        containerCurrentWeather.lifecycleOwner = viewLifecycleOwner
    }

    private fun initRvAdapter() = with(binding){
        rvWeatherHome.adapter = adapter
        rvWeatherHome.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun forecastItemClick(data: WeatherList) {

        //  findNavController().popBackStack()
    }

}

