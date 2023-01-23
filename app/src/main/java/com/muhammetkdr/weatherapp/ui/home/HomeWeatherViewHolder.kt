package com.muhammetkdr.weatherapp.ui.home

import com.muhammetkdr.weatherapp.base.BaseViewHolder
import com.muhammetkdr.weatherapp.databinding.ItemWeatherDaysBinding
import com.muhammetkdr.weatherapp.model.forecast.WeatherList
import javax.inject.Inject

class HomeWeatherViewHolder @Inject constructor (
    private val binding: ItemWeatherDaysBinding,
    private val onItemClickListener: ((WeatherList) -> Unit)?
) : BaseViewHolder<WeatherList>(binding.root) {
    override fun onBind(data: WeatherList) {
        binding.weatherList = data
        itemView.setOnClickListener { onItemClickListener?.invoke(data) }
        }
}