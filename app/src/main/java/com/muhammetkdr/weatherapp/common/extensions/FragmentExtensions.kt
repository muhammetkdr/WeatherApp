package com.muhammetkdr.weatherapp.common.extensions

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


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

fun Fragment.showSafeSnackbar(msg:String){
    try {
        Snackbar.make(requireView(),msg, Snackbar.LENGTH_LONG)
            .show()
    } catch (e:Exception){
        println(e.printStackTrace())
    }
}

fun <T> Fragment.collectFlow(
    flow: Flow<T>,
    action: suspend (T) -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch {
            flow.flowWithLifecycle(lifecycle).collect{
                action(it)
            }
    }
}