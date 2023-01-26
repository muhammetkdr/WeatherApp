package com.muhammetkdr.weatherapp.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.muhammetkdr.weatherapp.common.logger.LoggerImpl
import com.muhammetkdr.weatherapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.lang.IllegalArgumentException
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var logger: LoggerImpl


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        logger.log("onCreate")

    }

    override fun onStart() {
        super.onStart()
        logger.log("onStart")

    }
    override fun onResume() {
        super.onResume()
        logger.log("onResume")
    }

    override fun onPause() {
        super.onPause()
        logger.log("onPause")
    }

    override fun onStop() {
        super.onStop()
        logger.log("onStop")
    }

    override fun onDestroy() {
        logger.log("onDestroy")
        super.onDestroy()

    }

}