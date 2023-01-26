package com.muhammetkdr.weatherapp.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.muhammetkdr.weatherapp.common.logger.Logger
import com.muhammetkdr.weatherapp.common.logger.LoggerImpl
import java.lang.IllegalArgumentException
import javax.inject.Inject

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB
) : Fragment() {

    private var _binding: VB? = null
    protected val binding: VB get() = _binding as VB

    protected abstract val viewModel: VM

    @Inject
    lateinit var logger:LoggerImpl

    override fun onAttach(context: Context) {
        super.onAttach(context)
        logger.log("onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logger.log("onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)

        if (_binding == null) {
            throw IllegalArgumentException("Binding null")
        }
        logger.log("onCreateViewF")

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        logger.log("onStartF")

    }

    override fun onResume() {
        super.onResume()
        logger.log("onResumeF")
    }

    override fun onPause() {
        super.onPause()
        logger.log("onPauseF")
    }

    override fun onStop() {
        super.onStop()
        logger.log("onStopF")
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