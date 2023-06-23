package com.muhammetkdr.weatherapp.common.extensions

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
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

fun <T> Fragment.collectFlow(
    flow: Flow<T>,
    state: Lifecycle.State = Lifecycle.State.STARTED,
    action: suspend (T) -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(state) {
            flow.collect {
                action(it)
            }
        }
    }
}