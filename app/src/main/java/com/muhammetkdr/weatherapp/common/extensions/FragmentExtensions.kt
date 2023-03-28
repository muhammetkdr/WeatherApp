package com.muhammetkdr.weatherapp.common.extensions

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar


inline fun Fragment.addOnBackPressedDispatcher(crossinline onBackPressed: () -> Unit) {
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

inline fun Fragment.showSafeSnackbar(msg:String, actionMsg:String, crossinline handler: () -> Unit){
    try {
        Snackbar.make(requireView(),msg, Snackbar.LENGTH_INDEFINITE).apply {
            setAction(actionMsg){
                handler()
            }
            show()
        }
    } catch (e:Exception){
        println(e.printStackTrace())
    }
}