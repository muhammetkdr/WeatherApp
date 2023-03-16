package com.muhammetkdr.weatherapp.common.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.muhammetkdr.weatherapp.R
import com.muhammetkdr.weatherapp.common.utils.Constants.IMAGE_RESOLUTION_SIZE
import com.muhammetkdr.weatherapp.common.utils.Constants.WEATHER_IMAGE_BASE_URL

@BindingAdapter("setWeatherImage")
fun ImageView.setWeatherImage(weatherCondition:String?) {
    Glide.with(this).load(WEATHER_IMAGE_BASE_URL + weatherCondition + IMAGE_RESOLUTION_SIZE)
        .placeholder(R.drawable.ic_downloading)
        .error(R.drawable.ic_error)
        .into(this)
}