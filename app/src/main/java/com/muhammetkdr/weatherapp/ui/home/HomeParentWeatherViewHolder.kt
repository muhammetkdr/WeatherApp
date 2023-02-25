package com.muhammetkdr.weatherapp.ui.home

import com.muhammetkdr.weatherapp.base.BaseViewHolder
import com.muhammetkdr.weatherapp.databinding.ItemParentForecastRvBinding
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.DatesAndTimes
import javax.inject.Inject

class HomeParentWeatherViewHolder @Inject constructor(
    private val binding : ItemParentForecastRvBinding,
    private val onItemClickListener: ((String) -> Unit)?
) : BaseViewHolder<DatesAndTimes>(binding.root) {

    override fun onBind(data: DatesAndTimes) {

        binding.tvParentDate.text = data.date
        itemView.setOnClickListener {
            onItemClickListener?.invoke(data.date)
        }

        val adapter = HomeChildWeatherAdapter(onItemClickListener)
        binding.childRv.adapter = adapter
        adapter.submitList(data.hours)
    }


}