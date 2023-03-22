package com.muhammetkdr.weatherapp.ui.home.nestedrv

import com.muhammetkdr.weatherapp.base.BaseViewHolder
import com.muhammetkdr.weatherapp.databinding.ItemParentForecastRvBinding
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.forecastuidata.DatesAndTimes
import javax.inject.Inject

class HomeParentForecastWeatherViewHolder @Inject constructor(
    private val binding: ItemParentForecastRvBinding,
    private val onItemClickListener: ((DatesAndTimes) -> Unit)?
) : BaseViewHolder<DatesAndTimes>(binding.root) {

    override fun onBind(data: DatesAndTimes) {
        binding.datesAndTimes = data

        binding.rvParentCard.setOnClickListener {
            onItemClickListener?.invoke(data)
        }

        val adapter = HomeChildForecastWeatherAdapter()
        binding.childRv.adapter = adapter

        binding.childRv.suppressLayout(true)

        adapter.submitList(data.childRvUiData)
    }

}