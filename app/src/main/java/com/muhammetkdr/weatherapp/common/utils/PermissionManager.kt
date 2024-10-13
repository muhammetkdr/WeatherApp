package com.muhammetkdr.weatherapp.common.utils

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionManager(
    private val activity: ComponentActivity, // Artık Activity alıyoruz
    private val permissions: Array<String>,
    private val onPermissionGranted: (() -> Unit),
    private val onPermissionDenied: (() -> Unit)
) {

    private val requestPermissionLauncher: ActivityResultLauncher<Array<String>> =
        activity.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (permissions.all { it.value }) {
                onPermissionGranted.invoke()
            } else {
                onPermissionDenied.invoke()
            }
        }

    fun requestPermissions() {
        if (permissions.all { activity.hasPermission(it) }) {
            onPermissionGranted.invoke()
        } else {
            if (shouldShowPermissionRationale()) {
                showRationaleSnackbar()
            } else {
                requestPermissionLauncher.launch(permissions)
            }
        }
    }

    private fun shouldShowPermissionRationale(): Boolean {
        return permissions.any {
            ActivityCompat.shouldShowRequestPermissionRationale(activity, it)
        }
    }

    private fun showRationaleSnackbar() {
        /**
         * If user reject the permission first time
         *  this block will be executed
         *  asking for permissions again in this block not a good idea
         *  but it's just a demo to be able to see how works is that
         */
        requestPermissionLauncher.launch(permissions)
    }

    private fun Context.hasPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            permission
        ) == android.content.pm.PackageManager.PERMISSION_GRANTED
    }
}
