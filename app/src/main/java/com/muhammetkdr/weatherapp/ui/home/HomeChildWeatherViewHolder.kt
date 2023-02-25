package com.muhammetkdr.weatherapp.ui.home

import android.view.View
import com.muhammetkdr.weatherapp.base.BaseViewHolder
import com.muhammetkdr.weatherapp.databinding.ItemChildWeatherDaysBinding
import javax.inject.Inject

class HomeChildWeatherViewHolder @Inject constructor (
    private val binding: ItemChildWeatherDaysBinding,
    private val onItemClickListener: ((String) -> Unit)?
) : BaseViewHolder<String>(binding.root) {
    override fun onBind(data: String) {
        binding.tvItemWeatherTime.text = data
        itemView.setOnClickListener { onItemClickListener?.invoke(data) }
        binding.imgWeatherCondition.visibility = View.GONE
        binding.tvItemWeatherDegree.visibility = View.GONE
//        val adapter = ChildAdapter()
//        binding.chilRV.adapter = adapter
//        adapter.submitList(datesAndTimes.hours)
//        binding.title.text = datesAndTimes.date
        }
}