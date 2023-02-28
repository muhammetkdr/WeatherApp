package com.muhammetkdr.weatherapp.ui.home.nestedrv

import com.muhammetkdr.weatherapp.base.BaseViewHolder
import com.muhammetkdr.weatherapp.data.dto.forecast.WeatherList
import com.muhammetkdr.weatherapp.databinding.ItemChildWeatherDaysBinding
import javax.inject.Inject

class HomeChildForecastWeatherViewHolder @Inject constructor(
    val binding: ItemChildWeatherDaysBinding,
    private val onItemClickListener: ((String) -> Unit)?
) :
    BaseViewHolder<String>(binding.root) {
    override fun onBind(data: String) {


//        binding.weatherList = data
        itemView.setOnClickListener {
            onItemClickListener?.invoke(data)
        }
        binding.tvItemWeatherTime.text = data
    }
}