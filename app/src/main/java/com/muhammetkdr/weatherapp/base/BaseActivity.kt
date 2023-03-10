package com.muhammetkdr.weatherapp.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.muhammetkdr.weatherapp.R
import com.muhammetkdr.weatherapp.common.logger.Logger
import com.muhammetkdr.weatherapp.common.logger.LoggerImpl
import javax.inject.Inject

abstract class BaseActivity<VB : ViewBinding>(private val inflate: (LayoutInflater) -> VB) : AppCompatActivity() {

    private var _binding: VB? = null
    protected val binding: VB
        get() = _binding ?: throw IllegalStateException(getString(R.string.binding_null))

    @Inject
    lateinit var logger: LoggerImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        logger.log("onCreate")

        _binding = inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        logger.log("onStart")
        super.onStart()
    }

    override fun onResume() {
        logger.log("onResume")
        super.onResume()
    }

    override fun onRestart() {
        logger.log("onRestart")
        super.onRestart()
    }

    override fun onPause() {
        logger.log("onPause")
        super.onPause()
    }

    override fun onStop() {
        logger.log("onStop")
        super.onStop()
    }

    override fun onDestroy() {
        logger.log("onDestroy")
        super.onDestroy()
        _binding = null
    }
}