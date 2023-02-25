package com.muhammetkdr.weatherapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muhammetkdr.weatherapp.base.BaseListAdapter
import com.muhammetkdr.weatherapp.databinding.ItemParentForecastRvBinding
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.DatesAndTimes
import javax.inject.Inject

class HomeParentWeatherAdapter @Inject constructor (private val onCategoryItemClickListener: ((String) -> Unit)?) :
    BaseListAdapter<DatesAndTimes>(
        itemsSame = { old, new -> old == new },
        contentsSame = { old, new -> old == new }){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding = ItemParentForecastRvBinding.inflate(inflater,parent,false)
        return HomeParentWeatherViewHolder(binding,onCategoryItemClickListener)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeParentWeatherViewHolder -> {
                getItem(position)?.let { item ->
                    holder.onBind(item)
                }
            }
        }
    }

}