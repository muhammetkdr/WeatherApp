package com.muhammetkdr.weatherapp.ui.home.nestedrv

import com.muhammetkdr.weatherapp.base.BaseViewHolder
import com.muhammetkdr.weatherapp.databinding.ItemParentForecastRvBinding
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.DatesAndTimes
import javax.inject.Inject

class HomeParentForecastWeatherViewHolder @Inject constructor(
    private val binding: ItemParentForecastRvBinding,
    private val onItemClickListener: ((DatesAndTimes) -> Unit)?
) : BaseViewHolder<DatesAndTimes>(binding.root) {

    override fun onBind(data: DatesAndTimes) {

        val adapter = HomeChildForecastWeatherAdapter()
        binding.childRv.adapter = adapter
        adapter.differForHours.submitList(data.hours)
        adapter.differForWeatherList.submitList(data.list)

        adapter.setOnChildItemClickListener {
            binding.root.callOnClick()
        }

        binding.datesAndTimes = data
        itemView.setOnClickListener {
            onItemClickListener?.invoke(data)
        }

    }
}