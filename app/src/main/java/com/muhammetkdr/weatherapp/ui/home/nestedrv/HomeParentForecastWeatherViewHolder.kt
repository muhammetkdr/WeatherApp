package com.muhammetkdr.weatherapp.ui.home.nestedrv

import com.muhammetkdr.weatherapp.base.BaseViewHolder
import com.muhammetkdr.weatherapp.data.dto.forecast.WeatherList
import com.muhammetkdr.weatherapp.databinding.ItemParentForecastRvBinding
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.DatesAndTimes
import javax.inject.Inject

class HomeParentForecastWeatherViewHolder @Inject constructor(
    private val binding: ItemParentForecastRvBinding,
    private val onItemClickListener: ((String) -> Unit)?
) : BaseViewHolder<DatesAndTimes>(binding.root) {

    override fun onBind(data: DatesAndTimes) {

        binding.datesAndTimes = data
        itemView.setOnClickListener {
            onItemClickListener?.invoke(data.date)
        }

        val onChildItemClickListener: ((String) -> Unit) = { data.date }

        val adapter = HomeChildForecastWeatherAdapter(onChildItemClickListener)
        binding.childRv.adapter = adapter
        adapter.submitList(data.hours)
//        adapter.differForHours.submitList(data.hours)
//        adapter.differForWeatherList.submitList(data.list)


    }

}