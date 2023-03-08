package com.muhammetkdr.weatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.muhammetkdr.weatherapp.common.logger.LoggerImpl
import com.muhammetkdr.weatherapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var logger: LoggerImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logger.log("onCreate")
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
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
    }

}