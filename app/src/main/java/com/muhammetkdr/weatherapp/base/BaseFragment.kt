package com.muhammetkdr.weatherapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.muhammetkdr.weatherapp.R
import com.muhammetkdr.weatherapp.common.logger.Logger
import com.muhammetkdr.weatherapp.common.logger.LoggerImpl
import java.lang.IllegalArgumentException
import javax.inject.Inject

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB
) : Fragment() {

    private var _binding: VB? = null

    protected val binding: VB
        get() = _binding ?: throw IllegalStateException(getString(R.string.binding_null))

    protected abstract val viewModel: VM

    @Inject
    lateinit var logger: LoggerImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        logger.log("onCreateF")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)

        logger.log("onCreateViewF")
        return binding.root
    }

    override fun onStart() {
        logger.log("onStartF")
        super.onStart()
    }

    override fun onResume() {
        logger.log("onResumeF")
        super.onResume()
    }

    override fun onPause() {
        logger.log("onPauseF")
        super.onPause()
    }

    override fun onStop() {
        logger.log("onStopF")
        super.onStop()
    }

    override fun onDestroyView() {
        logger.log("onDestroyViewF")
        _binding = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        logger.log("onDestroyF")
        super.onDestroy()
    }

    override fun onDetach() {
        logger.log("onDetachF")
        super.onDetach()
    }

}