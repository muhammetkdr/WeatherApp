package com.muhammetkdr.weatherapp.common.utils

import android.content.Context
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.muhammetkdr.weatherapp.common.extensions.showSafeSnackbar

class PermissionManager(
    private val fragment: Fragment,
    private val permissions: Array<String>,
    private val onPermissionGranted: (() -> Unit),
    private val onPermissionDenied: (() -> Unit),
) {

    private val requestPermissionLauncher: ActivityResultLauncher<Array<String>> =
        fragment.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (permissions.all { it.value }) {
                onPermissionGranted.invoke()
            } else {
                onPermissionDenied.invoke()
            }
        }

    fun requestPermissions() {
        if (permissions.all { fragment.requireContext().hasPermission(it) }) {
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
            fragment.shouldShowRequestPermissionRationale(it)
        }
    }

    private fun showRationaleSnackbar() {
        fragment.showSafeSnackbar(
            "İzin gerekiyor",
            "İzin Ver"
        ) {
            requestPermissionLauncher.launch(permissions)
        }
    }

    private fun Context.hasPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            permission
        ) == android.content.pm.PackageManager.PERMISSION_GRANTED
    }
}