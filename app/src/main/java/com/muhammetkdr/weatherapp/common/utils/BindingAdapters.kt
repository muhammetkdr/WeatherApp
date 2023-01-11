package com.muhammetkdr.weatherapp.common.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("setImage")
fun ImageView.setImage(imageUrl: String?) {
    Glide.with(this).load(imageUrl).into(this)
}