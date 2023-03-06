package com.muhammetkdr.weatherapp.ui.home.nestedrv

import com.muhammetkdr.weatherapp.base.BaseViewHolder
import com.muhammetkdr.weatherapp.databinding.ItemParentForecastRvBinding
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.ChildRvUiData
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.DatesAndTimes
import javax.inject.Inject

class HomeParentForecastWeatherViewHolder @Inject constructor(
    private val binding: ItemParentForecastRvBinding,
    private val onItemClickListener: ((DatesAndTimes) -> Unit)?
) : BaseViewHolder<DatesAndTimes>(binding.root) {

    override fun onBind(data: DatesAndTimes) {
        binding.datesAndTimes = data
        itemView.setOnClickListener {
            onItemClickListener?.invoke(data)
        }

        val adapter = HomeChildForecastWeatherAdapter(::onChildItemClick)
        binding.childRv.adapter = adapter
        adapter.submitList(data.childRvUiData)
    }
    fun onChildItemClick(data:ChildRvUiData){
        binding.root.callOnClick()
    }
}