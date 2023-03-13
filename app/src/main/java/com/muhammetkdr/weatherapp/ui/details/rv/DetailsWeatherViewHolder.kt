package com.muhammetkdr.weatherapp.ui.details.rv

import com.muhammetkdr.weatherapp.base.BaseViewHolder
import com.muhammetkdr.weatherapp.databinding.ItemWeatherDetailsBinding
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.ChildRvUiData
import javax.inject.Inject

class DetailsWeatherViewHolder@Inject constructor(
    private val binding: ItemWeatherDetailsBinding,
    private val onItemClickListener: ((ChildRvUiData) -> Unit)?
) : BaseViewHolder<ChildRvUiData>(binding.root) {
    override fun onBind(data: ChildRvUiData) {
        binding.childData = data
        itemView.setOnClickListener {
            onItemClickListener?.invoke(data)
        }
    }
}