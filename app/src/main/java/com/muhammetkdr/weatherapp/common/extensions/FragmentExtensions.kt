package com.muhammetkdr.weatherapp.common.extensions

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar


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

fun Fragment.showSafeSnackbar(msg:String, actionMsg:String, handler: () -> Unit){
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