package com.muhammetkdr.weatherapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muhammetkdr.weatherapp.base.BaseListAdapter
import com.muhammetkdr.weatherapp.databinding.ItemChildWeatherDaysBinding
import javax.inject.Inject

class HomeChildWeatherAdapter @Inject constructor (private val onCategoryItemClickListener: ((String) -> Unit)?) :
    BaseListAdapter<String>(
        itemsSame = { old, new -> old == new },
        contentsSame = { old, new -> old == new }) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding =
            ItemChildWeatherDaysBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeChildWeatherViewHolder(binding, onCategoryItemClickListener)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeChildWeatherViewHolder -> {
                getItem(position)?.let { item ->
                    holder.onBind(item)
                }
            }
        }
    }
}