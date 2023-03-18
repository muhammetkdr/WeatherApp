package com.muhammetkdr.weatherapp.ui.details.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muhammetkdr.weatherapp.base.BaseListAdapter
import com.muhammetkdr.weatherapp.databinding.ItemWeatherDetailsBinding
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.forecastuidata.ChildRvUiData
import javax.inject.Inject

class DetailsWeatherAdapter @Inject constructor(
    private val onItemClickListener: ((ChildRvUiData) -> Unit)?
) : BaseListAdapter<ChildRvUiData>(
    itemsSame = { old, new -> old == new },
    contentsSame = { old, new -> old == new }) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder  {

        val binding = ItemWeatherDetailsBinding.inflate(inflater,parent,false)
        return DetailsWeatherViewHolder(binding,onItemClickListener)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DetailsWeatherViewHolder -> {
                getItem(position)?.let { item ->
                    holder.onBind(item)
                }
            }
        }
    }

}