package com.muhammetkdr.weatherapp.ui.details.rv

import com.muhammetkdr.weatherapp.base.BaseViewHolder
import com.muhammetkdr.weatherapp.databinding.ItemWeatherDetailsBinding
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.forecastuidata.ChildRvUiData
import javax.inject.Inject

class DetailsWeatherViewHolder@Inject constructor(
    private val binding: ItemWeatherDetailsBinding,
) : BaseViewHolder<ChildRvUiData>(binding.root) {
    override fun onBind(data: ChildRvUiData) {
        binding.childData = data
    }
}