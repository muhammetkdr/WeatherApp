package com.muhammetkdr.weatherapp.common.extensions

import android.Manifest
import android.content.Context
import com.vmadalin.easypermissions.EasyPermissions

fun Context.checkLocationPermission(): Boolean =
    EasyPermissions.hasPermissions(
        this,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

