package com.muhammetkdr.weatherapp.ui.home

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.muhammetkdr.weatherapp.R
import com.muhammetkdr.weatherapp.base.BaseFragment
import com.muhammetkdr.weatherapp.common.extensions.*
import com.muhammetkdr.weatherapp.common.utils.Const.Companion.LOCATION_REQUEST_DURATION
import com.muhammetkdr.weatherapp.common.utils.Const.Companion.REQUEST_CODE_LOCATION_PERMISSION
import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.common.utils.setImage
import com.muhammetkdr.weatherapp.databinding.FragmentHomeBinding
import com.muhammetkdr.weatherapp.location.DefaultLocationClient
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate
), EasyPermissions.PermissionCallbacks {
    override val viewModel by viewModels<HomeViewModel>()

    @Inject
    lateinit var defaultLocationClient: DefaultLocationClient

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requestLocationPermission()
        onPermissionRequestResult()

        initDataBinding()
        getLocation()
        initUi()
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() = lifecycleScope.launchWhenStarted {
        try {
            defaultLocationClient.getLocationUpdates(LOCATION_REQUEST_DURATION).collect {
                viewModel.getWeather(it.latitude, it.longitude)
            }
        } catch (e: Exception) {
            requireView().showSnackbar(
                e.localizedMessage
                    ?: requireContext().resources.getString(R.string.gps_or_network_disabled)
            )
        }
    }

    private fun initUi() {
        viewModel.weatherList.observe(viewLifecycleOwner) { Resource ->
            when (Resource) {
                is Resource.Success -> {
                    Resource.data.let {
                        binding.homeProgressbar.gone()
                        binding.containerForecast.weatherResponse = it
//                        binding.containerForecast.imgViewCustomHome.setImageResource(R.drawable.background)
                        binding.textView.text =
                            it.name.toString() + "\n" + it.main?.temp?.toString() + "\n" + it.weather?.get(0)?.main + "\n" + it.weather?.get(0)?.description?.capitalizeWords()
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

    private fun initDataBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun requestLocationPermission() {
        if (requireContext().checkLocationPermission()) {
            return
        }
        EasyPermissions.requestPermissions(
            this,
            requireContext().resources.getString(R.string.permission_denied_rationale),
            REQUEST_CODE_LOCATION_PERMISSION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            SettingsDialog.Builder(requireContext()).build().show()
        } else {
            requestLocationPermission()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        Toast.makeText(
            requireContext(),
            requireContext().resources.getString(R.string.permission_granted),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun onPermissionRequestResult() =
        object : ActivityCompat.OnRequestPermissionsResultCallback {
            override fun onRequestPermissionsResult(
                requestCode: Int,
                permissions: Array<out String>,
                grantResults: IntArray
            ) {
                EasyPermissions.onRequestPermissionsResult(
                    requestCode,
                    permissions,
                    grantResults,
                    this
                )
            }
        }
}

