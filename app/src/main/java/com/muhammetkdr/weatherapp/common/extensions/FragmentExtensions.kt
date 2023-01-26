package com.muhammetkdr.weatherapp.common.extensions

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment


fun Fragment.addOnBackPressedDispatcher(onBackPressed: () -> Unit) {
    requireActivity().onBackPressedDispatcher.addCallback(
        viewLifecycleOwner,
        object :
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressed()
            }
        }
    )
}