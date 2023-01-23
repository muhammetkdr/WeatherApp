package com.muhammetkdr.weatherapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muhammetkdr.weatherapp.base.BaseListAdapter
import com.muhammetkdr.weatherapp.databinding.ItemWeatherDaysBinding
import com.muhammetkdr.weatherapp.model.forecast.WeatherList
import javax.inject.Inject

class HomeWeatherAdapter @Inject constructor (private val onCategoryItemClickListener: ((WeatherList) -> Unit)?) :
    BaseListAdapter<WeatherList>(
        itemsSame = { old, new -> old == new },
        contentsSame = { old, new -> old == new }) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding =
            ItemWeatherDaysBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeWeatherViewHolder(binding, onCategoryItemClickListener)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeWeatherViewHolder -> {
                getItem(position)?.let { item ->
                    holder.onBind(item)
                }
            }
        }
    }
}