package com.muhammetkdr.weatherapp.common.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.muhammetkdr.weatherapp.R

@BindingAdapter("setImage")
fun ImageView.setImage(imageUrl: String?) {
    Glide.with(this).load(imageUrl)
        .placeholder(R.drawable.ic_downloading)
        .error(R.drawable.ic_error)
        .into(this)
}