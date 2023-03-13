package com.muhammetkdr.weatherapp.ui.home.nestedrv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muhammetkdr.weatherapp.base.BaseListAdapter
import com.muhammetkdr.weatherapp.databinding.ItemParentForecastRvBinding
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.DatesAndTimes
import javax.inject.Inject

class HomeParentForecastWeatherAdapter @Inject constructor(
    private val onItemClickListener: ((DatesAndTimes) -> Unit)?
) : BaseListAdapter<DatesAndTimes>(
        itemsSame = { old, new -> old == new },
        contentsSame = { old, new -> old == new }) {
        override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding = ItemParentForecastRvBinding.inflate(inflater, parent, false)

        return HomeParentForecastWeatherViewHolder(
            binding,
            onItemClickListener
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeParentForecastWeatherViewHolder -> {
                getItem(position)?.let { item ->
                    holder.onBind(item)
                }
            }
        }
    }
}