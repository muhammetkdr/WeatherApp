package com.muhammetkdr.weatherapp.common.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.muhammetkdr.weatherapp.BuildConfig
import com.muhammetkdr.weatherapp.R
import com.muhammetkdr.weatherapp.common.utils.Const.Companion.IMAGE_RESOLUTION_SIZE

@BindingAdapter("setWeatherImage")
fun ImageView.setWeatherImage(weatherCondition:String?) {
    Glide.with(this).load(BuildConfig.IMAGE_URL + weatherCondition + IMAGE_RESOLUTION_SIZE)
        .placeholder(R.drawable.ic_downloading)
        .error(R.drawable.ic_error)
        .into(this)
}