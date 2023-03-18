package com.muhammetkdr.weatherapp.ui.home.nestedrv

import com.muhammetkdr.weatherapp.base.BaseViewHolder
import com.muhammetkdr.weatherapp.databinding.ItemChildWeatherDaysBinding
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.forecastuidata.ChildRvUiData
import javax.inject.Inject

class HomeChildForecastWeatherViewHolder @Inject constructor(
    private val binding: ItemChildWeatherDaysBinding,
    private val onItemClickListener: ((ChildRvUiData) -> Unit)?
) : BaseViewHolder<ChildRvUiData>(binding.root) {
    override fun onBind(data: ChildRvUiData) {
        binding.childUiData = data
        itemView.setOnClickListener {
            onItemClickListener?.invoke(data)
        }
    }
}